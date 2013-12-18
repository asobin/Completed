
public class CLL<E> implements CircularListADT<E>{

 private Listnode<E> curr;
 private int numItems;

 public CLL(){
  this.curr = null;
  this.numItems = 0;
 }

 

 public void add(E item) {
  Listnode<E> newNode = new Listnode<E>(item);
  if (isEmpty()) {
   curr = newNode;
   curr.setNext(curr);
   curr.setPrevious(curr);
   numItems = 1;
  } else {
   newNode.setNext(curr);
   newNode.setPrevious(curr.getPrevious());
   newNode.getNext().setPrevious(newNode);
   newNode.getPrevious().setNext(newNode);
   curr = newNode;
   numItems++;
  }
 }


 public E remove() throws ElementNotFoundException {
  if(isEmpty() == true){
   throw new ElementNotFoundException();
  }
  E data = curr.getData();
  Listnode<E> tmp = curr.getPrevious();
  Listnode<E> tmp2 = curr.getNext();
  tmp.setNext(tmp2);
  tmp2.setPrevious(tmp);
  curr = tmp2;
  numItems--;
  
 

  return data;
 }


 public E get(int offset) throws ElementNotFoundException {
  if(isEmpty() == true){
   throw new ElementNotFoundException();
  }
  if(offset >= 0){
   Listnode<E> tmp = curr;
   if(offset > size()){
    offset = size() % offset;
   }
   for(int i = 0; i < offset; i++){
    tmp = tmp.getNext();
   }
   return tmp.getData();
  }else{
   int offset2 = Math.abs(offset);
   Listnode<E> tmp2 = curr;
   if(offset2 > size()){
    offset2 = size() % offset;
   }
   for(int i = 0; i < offset2; i++){
    tmp2 = tmp2.getPrevious();
   }
   return tmp2.getData();
  }
 }


 public boolean isEmpty() {
  if(size() == 0){
   return true;
  }
  return false;
 }


 public int size() {

  return numItems;
 }


 public void setCurrentPosition(int offset) {
  if(offset >= 0){
   
   if(offset > size()){
    
    offset = size() % offset;
   }
   for(int i = 0; i < offset; i++){
    curr = curr.getNext();
   }

  }else{
   
   int offset2 = Math.abs(offset);
   if(offset2 > size()){
    
    offset2 = size() % offset;
   }
   for(int i = 0; i < offset2; i++){
    curr = curr.getPrevious();
   }

  }

 }
 public String print(int offset){

  if(offset >= 0){
   
   if(offset > size()){
    offset = size() % offset;
   }
   
   Listnode<E> tmp = curr;
   String data = curr.getData().toString(); 
   
   for(int i = 0; i < numItems-1; i++){
    tmp = tmp.getNext();
    data += "\n" + tmp.getData().toString();
    
   }
   
   return data + "";
   

  }else{
   
   
   int offset2 = Math.abs(offset);
   if(offset2 > size()){
    offset2 = size() % offset;
   }
   
   Listnode<E> tmp2 = curr;
   String data2 = "";
   for(int i = 0; i < numItems; i++){
    tmp2 = tmp2.getPrevious();
    data2 += "\n" + tmp2.getData().toString();
   }
   
   return data2 + "";

  }

 }
 public CircularLinkedListIterator<E> iterator(){
  return new CircularLinkedListIterator<E>(this);
 }
 public Listnode<E> getCurr(){
  return curr;
 }
 public void next(){
  curr.getNext();
 }

}
