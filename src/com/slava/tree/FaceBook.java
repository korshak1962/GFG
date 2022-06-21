package com.slava.tree;

public class FaceBook {
    /*


Check if a binary tree is subtree of another binary tree


Tree 2
          10
        /    \
      4       6
       \
        30
Tree 1
              26
            /   \
          10     3
        /    \     \
      4       6      3
       \
        30


1. try to make DFS and get int[]


fromTree1 = new ArrayList<>();
fromTree2 = new ArrayList<>();

void getArrayFromTree(Node root, List<> lst){
lst.add(root.value);
if (root.left!=null) getArrayFromTree(root.left);
if (root.right!=null) getArrayFromTree(root.right);
return ;
}

boolean isArrayInOther(int[Integer] lst1,int[Integer] lst2 ){
if (ls2.size()>ls1.size){
for (int i2=0;i2<ls2.size(); i2++){
   for (int i1=0;i1<ls1.size(); i1++){
    do while (ls2.get(i2)==ls1.get(i1)){
       i2++;i1++;
       if (i1==ls1.size()){ return true;
       }
    }
   }
}
return false;
}

main(){

void getArrayFromTree(Node root,fromTree1 );
void getArrayFromTree(Node root,fromTree2 );




}






["mad", "madness", "chair", ] ~ 200k words

mad t
asdfpoiansdpfiounasdiopfnui f
. <- one ocurrence of any character

madnes. t
.adnes. t
..... t
.................................................................... f


boolean isPresent(Node root, String toFind){
if (toFind.length==0) return true;
if (toFind.carAt(0)=='.') {
  for (Node child:root.getChilds()){
    if(!isPresent( child,toFind.subsrting(1,toFind.length));) continue;
  }
  for (Node child:root.getChilds()){
  if (toFind.charAt(0)==child.value) isPresent( child,toFind.subsrting(1,toFind.length));
  }

  return false;

}

// given tree leaf -> compute value of parent

// find max overlapping interval

// split string into words















     */


}
