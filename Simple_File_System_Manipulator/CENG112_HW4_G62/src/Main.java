import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fs.*;
import tree.*;

public class Main {
    private static String buildPathString(Tree<FsEntry> fsTree) {
        String path = fsTree.getData().getName();
        Tree<FsEntry> temp = fsTree.getParent();
        while(temp != null && !temp.getData().getName().equals("")) {
            path = temp.getData().getName() + "/" + path;
            temp = temp.getParent();
        }
        return "/" + path;
    }
    
    private static List<String> getPathsToBeDeleted(String rootPath, GenericTree<FsEntry> fsTree) {
        List<String> pathsToBeDeleted = new ArrayList<>();
        Stack<GenericTree<FsEntry>> treeStack = new Stack<>();
        treeStack.push(fsTree);
        while(!treeStack.isEmpty()) {
            fsTree = treeStack.pop();
            pathsToBeDeleted.add(rootPath + buildPathString(fsTree));
            if(fsTree.isLeaf()) continue;
            for(Tree<FsEntry> child: fsTree.getChildren())
                treeStack.add((GenericTree<FsEntry>) child);
        }
        return pathsToBeDeleted;
    }
    
    private static BinarySearchTree<String, GenericTree<FsEntry>> buildPathSearchTree(GenericTree<FsEntry> fsTree) {
        BinarySearchTree<String, GenericTree<FsEntry>> searchTree = new BinarySearchTree<>();
        Stack<Tree<FsEntry>> treeStack = new Stack<>();
        treeStack.push(fsTree);
        while(!treeStack.isEmpty()) {
            fsTree = (GenericTree<FsEntry>) treeStack.pop();            
            searchTree.add(buildPathString(fsTree), fsTree);
            if(fsTree.isLeaf()) continue;
            for(Tree<FsEntry> child: fsTree.getChildren())
                treeStack.add(child);
        }
        return searchTree;
    }
    
    private static GenericTree<FsEntry> buildFsTree(Iterable<FsEntry> entries) {
        BinarySearchTree<Integer, GenericTree<FsEntry>> searchTree = new BinarySearchTree<>();
        for(FsEntry entry: entries) {
            searchTree.add(entry.getId(), new GenericTree<FsEntry>(entry));
        }
        
        for(FsEntry entry: entries) {
            if(entry.getType() == FsEntryType.DIR) {
                GenericTree<FsEntry> dir = searchTree.find(entry.getId()).getData();
                for(String childId: entry.getContent()) {
                    dir.addChild(searchTree.find(Integer.parseInt(childId)).getData());
                }
            }
        }
        
        return searchTree.find(0).getData();
    }
    
    private static void parseAndApplyCommand(String line, GenericTree<FsEntry> fsTree,  BinarySearchTree<String, GenericTree<FsEntry>> pathSearchTree) {
        if(line.length() == 0)
            return;
        
        String[] tokens = line.trim().replaceAll("\\s{2,}", " ").split(" ");
        if(tokens.length != 2) {
            System.err.println("[ERROR] All commands must consist of 2 tokens");
            return;
        }
        
        pathSearchTree = pathSearchTree.find(tokens[1]);
        if(pathSearchTree == null) {
            System.err.println("Path " + tokens[1] + " doesn't exist");
            return;
        }
        
        GenericTree<FsEntry> file = pathSearchTree.getData();
        switch (tokens[0]) {
        case "showContent":
            for(String content: file.getData().getContent())
                System.out.println(content);
            break;
        case "getHeight":
            System.out.println(file.getHeight());
            break;
        case "getNumberOfFiles":
            System.out.println(file.getNumberOfNodes());
            break;
        case "removeFile":
            if(file.getParent() != null) {
                GenericTree<FsEntry> parent = ((GenericTree<FsEntry>)file.getParent());
                List<String> content = Stream.of(parent.getData().getContent())
                                             .collect(Collectors.toList());
                content.remove(Integer.toString(file.getData().getId()));
                parent.getData().setContent(content.toArray(new String[content.size()]));
                parent.removeChild(file);
            }
            String rootPath = tokens[1].substring(0, tokens[1].lastIndexOf("/"));
            getPathsToBeDeleted(rootPath, file).forEach(pathSearchTree::remove);
            break;
        case "traversePreOrderRecursively":
            file.traversePreOrderRecursively(data -> System.out.println(data.getName()));
            break;
        case "traverseLevelOrder":
            file.traverseLevelOrder(data -> System.out.println(data.getName()));
            break;
        case "traversePostOrderIteratively":
            file.traversePostOrderIteratively(data -> System.out.println(data.getName()));
            break;
        default:
            System.err.println("Command not found");
            break;
        }
    }
    
    public static void main(String[] args) throws Exception {
        Iterable<FsEntry> allFsEntries = FsIO.getAllEntries("fs");
        GenericTree<FsEntry> fsTree = buildFsTree(allFsEntries);
        BinarySearchTree<String, GenericTree<FsEntry>> pathSearchTree = buildPathSearchTree(fsTree);
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("$ ");
            if(sc.hasNextLine()) {
                parseAndApplyCommand(sc.nextLine(), fsTree, pathSearchTree);
            } else {
                return;
            }
        }
    }   
}