/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birds;

/**
 *
 * @author James
 */
public class OrderedDictionary implements OrderedDictionaryADT{
    private BinaryNode<BirdRecord> root = new BinaryNode<BirdRecord>();
    
    public OrderedDictionary(){
        root.setData(null);
        root.setLeftChild(null);
        root.setRightChild(null);
        root.setParent(null);
    }
    
    /* Returns the Record object with key k, or It throws a DictionaryException
       says: "There is no record matches the given key", if such a record
       is not in the dictionary. 

       @param k
       @return BirdRecord
       @throws DictionaryException
     */
    public BirdRecord find(DataKey k) throws DictionaryException{
            BinaryNode<BirdRecord> temp = new BinaryNode<BirdRecord>();
            temp.setLeftChild(root);
            System.out.println("checkpoint 1");
            if (isEmpty()==true){throw new DictionaryException("Dictionary is empty");}
            System.out.println("checkpoint 2");
            boolean found = false;
            while( !found){ // finish    /*temp.getLeftChild()!=/){
                    int position = k.compareTo(   temp.getLeftChild().getData().getDataKey()   );     //compare to r's data
                    if (position == 0){
                        found = true;
                    }
                    if (position <= -1){
                        if (temp.getLeftChild().hasLeftChild()==false){  
                            throw new DictionaryException("There is no record that matches the given key");
                            //throw new DictionaryException("Record is already in the Dictionary");
                        }
                        temp.setLeftChild(temp.getLeftChild().getLeftChild());
                        continue;
                    }
                    if (position >= 1){
                        //if (root.hasRightChild()==false){  root.setRightChild(inserted);   }
                        if (temp.getLeftChild().hasRightChild()==false){  
                            throw new DictionaryException("There is no record that matches the given key");
                        }
                        temp.setLeftChild(temp.getLeftChild().getRightChild());
                        continue;
                    }  
                    //System.out.println("A find loop");
            }
            return temp.getLeftChild().getData();       //found, returns that node's birdRecord
            //check recusively, forever until null
                //if a leaf, and comparison is not 0, then failed --> no record matches the given key
                //if compareTo = 0, found, return what we've found and recorded

    }

    /* Inserts r into the ordered dictionary. It throws a DictionaryException 
       if a record with the same key as r is already in the dictionary.  

       @param r
       @throws DictionaryException
     */
    public void insert(BirdRecord r) throws DictionaryException{
            if (root.getData()==null){   
               // System.out.println("inserted to the root:" + r.getDataKey().getBirdName());
                root.setData(r);  
                System.out.println("Set "+r.getDataKey().getBirdName()+ " as the root");
                return;
            }                       //before was empty
            
                BinaryNode<BirdRecord> temp = new BinaryNode<BirdRecord>();
                temp.setLeftChild(root);
                
                BinaryNode<BirdRecord> inserted = new BinaryNode<BirdRecord>(r);
                //boolean finish = true;
                
                while(  true /* finish    /*temp.getLeftChild()!=*/){
                    int position = r.getDataKey().compareTo(   temp.getLeftChild().getData().getDataKey()   );     //compare root to r's data
                    if (position == 0){
                        System.out.println("nothing triggered, but escaped");
                        throw new DictionaryException("Record is already in the Dictionary");
                    }
                    if (position <= -1){
                        if (temp.getLeftChild().hasLeftChild()==false){  
                            temp.getLeftChild().setLeftChild(inserted);
                            inserted.setParent(temp.getLeftChild());
                            //System.out.println("inserted to the left");
                            System.out.println("Set "+r.getDataKey().getBirdName()+ " as the left child of " + temp.getLeftChild().getData().getDataKey().getBirdName());
                            
                            return;
                        }
                        temp.setLeftChild(temp.getLeftChild().getLeftChild());
                        System.out.println("moved left");
                        continue;
                    }
                    if(position >= 1){
                        //if (root.hasRightChild()==false){  root.setRightChild(inserted);   }
                        if (temp.getLeftChild().hasRightChild()==false){  
                            temp.getLeftChild().setRightChild(inserted);
                            inserted.setParent(temp.getLeftChild());
                            //System.out.println("inserted to the right");

                                System.out.println("Set "+r.getDataKey().getBirdName()+ " as the right child of " + temp.getLeftChild().getData().getDataKey().getBirdName());
                            
                            return;
                        }
                        temp.setLeftChild(temp.getLeftChild().getRightChild());
                        System.out.println("moved right");
                        continue;
                    }       
                    //System.out.println("Looped");
                }  //end of while loop
            //System.out.println("nothing triggered, but escaped");
     
    }

    /*  Removes the record with Key k from the dictionary. It throws a 
        DictionaryException says: "No such record key exists", if the record
        is not in the dictionary. 
             
       @param k
       @throws DictionaryException
     */
    public void remove(DataKey k) throws DictionaryException{

            BinaryNode<BirdRecord> temp = new BinaryNode<BirdRecord>();                                                     //Global?
            temp.setData(null);
            temp.setLeftChild(null);
            temp.setRightChild(null);
            BinaryNode<BirdRecord> temp2 = new BinaryNode<BirdRecord>();                                                    //Global?
            temp2.setData(null);
            temp2.setLeftChild(null);
            temp2.setRightChild(null);
            BinaryNode<BirdRecord> temp3 = new BinaryNode<BirdRecord>();
            temp3.setData(null);
            temp3.setLeftChild(null);
            temp3.setRightChild(null);
            
            temp.setLeftChild(root);

            boolean found = false;
            if (isEmpty()==true){
                throw new DictionaryException("No such record key exists");
            }
            
            //change logic
            while(  !found /* finish    /*temp.getLeftChild()!=*/){
                    int position = k.compareTo(   temp.getLeftChild().getData().getDataKey()   );     //compare root to r's data
                    if (position == 0){
                        temp2.setLeftChild(temp.getLeftChild());       //found, returns that nodes birdRecord                                   //CHanged
                        found =true;
                        continue;
                    }
                    if (position <= -1){
                        if (temp.getLeftChild().hasLeftChild()==false){  
                            throw new DictionaryException("There is no record that matches the given key");                                        //CHANGE?
                            //throw new DictionaryException("Record is already in the Dictionary");
                        }
                        temp.setLeftChild(temp.getLeftChild().getLeftChild());
                        continue;
                    }
                    if (position >= 1){
                        //if (root.hasRightChild()==false){  root.setRightChild(inserted);   }
                        if (temp.getLeftChild().hasRightChild()==false){  
                            throw new DictionaryException("There is no record that matches the given key");                                         //CHANGE?
                        }   
                        temp.setLeftChild(temp.getLeftChild().getRightChild());
                        continue;
                    }           
            }
            //Remove procedures;
            //Remove the largest
            if((temp2.getLeftChild().getData()==largest())&&(temp2.getLeftChild().hasParent())){
                //has left child,
                int trigger = 1;
                if (temp2.getLeftChild().hasLeftChild()==true){
                    temp3.setLeftChild(temp2.getLeftChild().getLeftChild());
                    trigger =0;
                }
                //no left child
                temp2.getLeftChild().getParent().setRightChild(null);
                temp2.setLeftChild(temp2.getLeftChild().getParent());
                //fix left child
                if (trigger ==0){
                    temp2.getLeftChild().setRightChild(temp3.getLeftChild());
                    temp3.getLeftChild().setParent(temp2.getLeftChild());
                }
                return;    
            }
            //Remove the smallest
            if((temp2.getLeftChild().getData()==smallest())&&(temp2.getLeftChild().hasParent())){
                //has right child,
                int trigger = 1;
                if (temp2.getLeftChild().hasRightChild()==true){
                    temp3.setLeftChild(temp2.getLeftChild().getRightChild());
                    trigger =0;
                }
                //no right child
                temp2.getLeftChild().getParent().setLeftChild(null);
                temp2.setLeftChild(temp2.getLeftChild().getParent());
                //fix right child
                if (trigger ==0){
                    temp2.getLeftChild().setLeftChild(temp3.getLeftChild());
                    temp3.getLeftChild().setParent(temp2.getLeftChild());
                }
                return;  
            }
            
            //3 Remove the root
            if ((temp2.getLeftChild().getData().getDataKey().compareTo(root.getData().getDataKey()))==0){
                //temp2 is the root
                if((root.hasLeftChild()==false)&&(root.hasRightChild()==false)){
                    temp2.setData(null);
                    return;
                }
                
                if((root.hasLeftChild()==true)&&((root.hasRightChild()==false)&&(root.getLeftChild().hasRightChild()==false))){
                    root.setData(root.getLeftChild().getData());
                    root.setRightChild(  root.getLeftChild().getRightChild());
                    root.setLeftChild( root.getLeftChild().getLeftChild() );
                    root.setParent(null);
                    System.out.println("Root removed, new root left");
                    return;
                }
                if((root.hasLeftChild()==false)&&((root.hasRightChild()==true)&&(root.getRightChild().hasLeftChild()==true))){
                    root.setData(root.getRightChild().getData());
                    root.setLeftChild(  root.getRightChild().getLeftChild());
                    root.setRightChild( root.getRightChild().getRightChild() );
                    root.setParent(null);
                    System.out.println("Root removed, new root right");
                    return;
                }
                //get predessor
                temp.setLeftChild(root);
                //find predessor and select to temp2
                DataKey c = predecessor(root.getData().getDataKey()).getDataKey();

                while(  !found /* finish    /*temp.getLeftChild()!=*/){
                    int position = c.compareTo(   temp.getLeftChild().getData().getDataKey()   );     //compare root to r's data
                    if (position == 0){
                        temp2.setLeftChild(temp.getLeftChild());       //found, returns that nodes birdRecord                                   //CHanged
                        found =true;
                        continue;
                    }
                    if (position <= -1){
                        if (temp.getLeftChild().hasLeftChild()==false){  
                            throw new DictionaryException("There is no record that matches the given key");                                        //CHANGE?
                            //throw new DictionaryException("Record is already in the Dictionary");
                        }
                        temp.setLeftChild(temp.getLeftChild().getLeftChild());
                        continue;
                    }
                    if (position >= 1){
                        //if (root.hasRightChild()==false){  root.setRightChild(inserted);   }
                        if (temp.getLeftChild().hasRightChild()==false){  
                            throw new DictionaryException("There is no record that matches the given key");                                         //CHANGE?
                        }   
                        temp.setLeftChild(temp.getLeftChild().getRightChild());
                        continue;
                    }           
                }   //temp2 is predessor
                //rearaange
                //old points, new root
                temp3.setLeftChild(temp2.getLeftChild());
                //temp2.getLeftChild().getData().getDataKey());
                root.setData(temp3.getLeftChild().getData());
                System.out.println("Made root the predecessor, removed root");
                return;
                //return
            }
            //1 - Leafs, Removing Leafs
            if (temp2.getLeftChild().isLeaf() ==true){
                //1b
                if ((temp2.getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getParent().getLeftChild().getData().getDataKey()))==0){
                    temp2.getLeftChild().getParent().setLeftChild(null);
                    return;
                }
                //1a
                if ((temp2.getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getParent().getRightChild().getData().getDataKey()))==0){
                    temp2.getLeftChild().getParent().setRightChild(null);
                    return;
                }
            }
            //Nodes with children
            if (temp2.getLeftChild().isLeaf()==false){      //root, any parent
                //7 - Single child nodes
                if ((  (temp2.getLeftChild().hasLeftChild()==true)  &&  (temp2.getLeftChild().hasRightChild()==true)  )==false){
                    //7....right side of parent with single child
                    if ((temp2.getLeftChild().getData().getDataKey().compareTo(  temp2.getLeftChild().getParent().getRightChild().getData().getDataKey() )) == 0){   //it's the right child
                        //7a
                        if (temp2.getLeftChild().hasLeftChild()==false){
                            temp2.getLeftChild().getParent().setRightChild(temp2.getLeftChild().getRightChild());   //
                            temp2.getLeftChild().getParent().getRightChild().setParent(temp2.getLeftChild().getParent());   //Restablish Parent
                            return;
                        }
                        //7b
                        if (temp2.getLeftChild().hasRightChild()==false){
                            temp2.getLeftChild().getParent().setRightChild(temp2.getLeftChild().getLeftChild());   //
                            temp2.getLeftChild().getParent().getRightChild().setParent(temp2.getLeftChild().getParent());   //Restablish Parent
                            return;
                        }
                    }
                    //7... left side of parent with single child
                    if ((temp2.getLeftChild().getData().getDataKey().compareTo(  temp2.getLeftChild().getParent().getLeftChild().getData().getDataKey() )) == 0){   //it's the left child
                        //7d
                        if (temp2.getLeftChild().hasLeftChild()==false){
                            temp2.getLeftChild().getParent().setLeftChild(temp2.getLeftChild().getRightChild());   //
                            temp2.getLeftChild().getParent().getLeftChild().setParent(temp2.getLeftChild().getParent());   //Restablish Parent
                            return;
                        }
                        //7c
                        if (temp2.getLeftChild().hasRightChild()==false){
                            temp2.getLeftChild().getParent().setLeftChild(temp2.getLeftChild().getLeftChild());   //
                            temp2.getLeftChild().getParent().getLeftChild().setParent(temp2.getLeftChild().getParent()); //Restablish Parent
                            return;
                        }
                    }
                }
                //2 children.
                //take two children of child, store them, replace parent with child, reinsert children
                
                //6, predessor is a leaf -> 6a, successor is a leaf -> 6b
                //6a
                if (temp2.getLeftChild().getLeftChild().isLeaf()==true){
                    temp2.setRightChild(temp2.getParent());     //store parent of node to be removed
                    temp2.getLeftChild().getLeftChild().setRightChild(  temp2.getLeftChild().getRightChild()   );
                    temp2.getLeftChild().getRightChild().setParent(  temp2.getLeftChild().getLeftChild()  );    //Restablish 
                    temp2.setLeftChild(temp2.getLeftChild().getLeftChild());
                    temp2.getRightChild().setLeftChild(temp2.getLeftChild());
                    temp2.getLeftChild().setParent(temp2.getRightChild());
                    return;
                }
                //6b
                if (temp2.getLeftChild().getRightChild().isLeaf()==true){
                    temp2.setRightChild(temp2.getParent());     //store parent of node to be removed
                    temp2.getLeftChild().getRightChild().setLeftChild(  temp2.getLeftChild().getLeftChild()   );
                    temp2.getLeftChild().getLeftChild().setParent(  temp2.getLeftChild().getRightChild()  ); //Restablish heirarchy
                    temp2.setLeftChild(temp2.getLeftChild().getRightChild());
                    temp2.getRightChild().setLeftChild(temp2.getLeftChild());
                    temp2.getLeftChild().setParent(temp2.getRightChild());
                    return;
                }
                //Two children, both have children(s)
                //save both sides of left child
                if(temp2.getLeftChild().hasLeftChild()==true){
                    temp3.setLeftChild(temp2.getLeftChild().getLeftChild());
                }
                if(temp2.getLeftChild().hasRightChild()==true){
                    temp3.setRightChild(temp2.getLeftChild().getRightChild());
                }
                //replace node to be removed with left child
                    //right child of parent
                if ((temp2.getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getParent().getRightChild().getData().getDataKey()))==0){   //right child
                    //put left child in place of node, establish right/left/parent connections
                    temp2.getLeftChild().getParent().setRightChild(  temp2.getLeftChild().getLeftChild()  );
                    temp2.getLeftChild().getLeftChild().setParent(  temp2.getLeftChild().getParent()  );
                    
                    temp2.getLeftChild().getLeftChild().setRightChild( temp2.getLeftChild().getRightChild()  );
                    temp2.getLeftChild().getRightChild().setParent( temp2.getLeftChild().getLeftChild() );
                    //reinset children of left child if exist
                    if (temp3.hasLeftChild()==true){
                        insert( temp3.getLeftChild().getData() );
                    }
                    if (temp3.hasRightChild()==true){
                        insert( temp3.getRightChild().getData() );
                    }  
                    return;
                }
                    //left child of parent
                if ((temp2.getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getParent().getLeftChild().getData().getDataKey()))==0){   //left child
                    //put left child in place of node, establish right/left/parent connections
                    temp2.getLeftChild().getParent().setLeftChild(  temp2.getLeftChild().getLeftChild()  );
                    temp2.getLeftChild().getLeftChild().setParent(  temp2.getLeftChild().getParent()  );
                    
                    temp2.getLeftChild().getLeftChild().setRightChild( temp2.getLeftChild().getRightChild()  );
                    temp2.getLeftChild().getRightChild().setParent( temp2.getLeftChild().getLeftChild() );
                    //reinset children of left child if exist
                    if (temp3.hasLeftChild()==true){
                        insert( temp3.getLeftChild().getData() );
                    }
                    if (temp3.hasRightChild()==true){
                        insert( temp3.getRightChild().getData() );
                    }  
                    return;
                }
                System.out.println("No remove cases triggered");
                return;
            }    
        
    }

    /* Returns the successor of k (the record from the ordered dictionary 
       with smallest key larger than k); It throws a DictionaryException says:
       "There is no successor for the given record key", if the given key has 
       no successor. The given key DOES NOT need to be in the dictionary. 
         
       @param k
       @return BirdRecord
       @throws DictionaryException
     */
    public BirdRecord successor(DataKey k) throws DictionaryException{      //flipped from predescssor
       
            BinaryNode<BirdRecord> temp = new BinaryNode<BirdRecord>();
            BinaryNode<BirdRecord> temp2 = new BinaryNode<BirdRecord>();
            temp.setLeftChild(root);
            
            BirdRecord notDefined = new BirdRecord(k,"","","");
            int a=0;
            
            try{ 
                find(k);
            }catch(DictionaryException ex){
                a=1;
                insert(notDefined);                
            }
            
            boolean found = false;
            //change logic
            while(  !found /* finish    /*temp.getLeftChild()!=*/){
                    int position = k.compareTo(   temp.getLeftChild().getData().getDataKey()   );     //compare root to r's data
                    if (position == 0){
                        temp2.setLeftChild(temp.getLeftChild());       //found, returns that nodes birdRecord                                   //CHanged
                        found =true;
                        continue;
                    }
                    if (position <= -1){
                        if (temp.getLeftChild().hasLeftChild()==false){  
                            throw new DictionaryException("There is no record that matches the given key");                                        //CHANGE?
                            //throw new DictionaryException("Record is already in the Dictionary");
                        }
                        temp.setLeftChild(temp.getLeftChild().getLeftChild());
                        continue;
                    }
                    if (position >= 1){
                        //if (root.hasRightChild()==false){  root.setRightChild(inserted);   }
                        if (temp.getLeftChild().hasRightChild()==false){  
                            throw new DictionaryException("There is no record that matches the given key");                                         //CHANGE?
                        }   
                        temp.setLeftChild(temp.getLeftChild().getRightChild());
                        continue;
                    }           
            }
            //if (smallest().){throw new DictionaryException("Dictionary is empty");}
            //Node is found, get predecessor
            
            //get left / get parent from right
            //1
            if (temp2.getLeftChild().getData() ==  largest()){
                throw new DictionaryException("There is no successor for the given record key"); 
            }
            //System.out.println("predecessor 0000");
            
            if ((temp2.getLeftChild().isLeaf())&&(temp2.getLeftChild().hasParent())){
                System.out.println("successor 2222");
                //Wild turkey
                if ((temp2.getLeftChild().hasParent())&& (temp2.getLeftChild().getParent().getLeftChild()==temp2.getLeftChild())){
                           if(a==1){remove(notDefined.getDataKey());  }
                           return temp2.getLeftChild().getParent().getData();
                }
                 //2 //4 
                //changed
                if ((temp2.getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getParent().getRightChild().getData().getDataKey())) == 0){    //left node to parent
                   //hit null pointer
                   found = true;
                   while (found){
                       if ((temp2.getLeftChild().hasParent())&& (temp2.getLeftChild().getParent().getRightChild()==temp2.getLeftChild())){
                           temp2.setLeftChild(temp2.getLeftChild().getParent());
                       }
                       else
                           found = false;
                   }
                   /*while((temp2.getLeftChild().hasParent())&& ( (temp2.getLeftChild().getParent().getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getData().getDataKey()))==0 )){    //changed
                       temp2.setLeftChild(temp2.getLeftChild().getParent());
                   }*/
                   //System.out.println("should be right for Northern Wheater");
                   if(a==1){remove(notDefined.getDataKey());  }
                   return temp2.getLeftChild().getParent().getData();       //returns first right parent
                }
                //3 //5 //6 //8
                if ((temp2.getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getParent().getData().getDataKey())) == -1){     //right node to parent
                    if(a==1){remove(notDefined.getDataKey());  }
                    return temp2.getLeftChild().getParent().getData();       //returns bird record of parent     
                }
            }
            
            //For Magnificent Frigatebird
            
            
            if((temp2.getLeftChild().hasRightChild()==false)&&(temp2.getLeftChild().hasParent()==true)){
                System.out.println("successor 1111");
                //changed
                if(temp2.getLeftChild().getParent().hasRightChild()==true){
                    if (((temp2.getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getParent().getRightChild().getData().getDataKey())) == 0)
                    &&(temp2.getLeftChild().hasLeftChild()==true)){    //left node to parent
                        //hit null pointer
                        found = true;
                        while (found){
                            if ((temp2.getLeftChild().hasParent())&& (temp2.getLeftChild().getParent().getRightChild()==temp2.getLeftChild())){
                                temp2.setLeftChild(temp2.getLeftChild().getParent());
                            }
                            else
                                  found = false;
                        }
                        /*while((temp2.getLeftChild().hasParent())&& ( (temp2.getLeftChild().getParent().getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getData().getDataKey()))==0 )){    //changed
                           temp2.setLeftChild(temp2.getLeftChild().getParent());
                        }*/
                        //System.out.println("should be right for Northern Wheater");
                        if(a==1){remove(notDefined.getDataKey());  }
                        return temp2.getLeftChild().getParent().getData();       //returns first right parent
                    }
                }
                temp2.setRightChild(  temp2.getLeftChild().getParent() );
                if(a==1){  remove(k);  
                    return temp2.getRightChild().getParent().getData();
                }   
                return temp2.getRightChild().getData();
            }
       
            //10  //12
            if (  (temp2.getLeftChild().getRightChild().isLeaf()==false) && (temp2.getLeftChild().getRightChild().hasLeftChild()) ){    
                System.out.println("successor 4444");
                temp2.setLeftChild(temp2.getLeftChild().getRightChild().getLeftChild());
                //10
                if(temp2.getLeftChild().getLeftChild()==null){
                    //System.out.println("What Northen Cardinal should trigger");                     //changed
                    if(a==1){remove(notDefined.getDataKey());  }
                    return temp2.getLeftChild().getData();
                } 
                while(temp2.getLeftChild().hasLeftChild()){
                    if(a==1){remove(notDefined.getDataKey());  }
                    temp2.setLeftChild(temp2.getLeftChild().getLeftChild());
                }
                //11
                if(a==1){remove(notDefined.getDataKey());  }
                return temp2.getLeftChild().getData();
            }
            //15
            if(  (temp2.getLeftChild().getRightChild().isLeaf()==false) && (temp2.getLeftChild().getRightChild().hasLeftChild())==false  ){
                System.out.println("succcessor 5555");
                if(a==1){remove(notDefined.getDataKey());  }
                return temp2.getLeftChild().getRightChild().getData();
            }   
               //9
            if ((temp2.hasLeftChild()==true)&&(temp2.getLeftChild().getRightChild().isLeaf()==true)){    
                System.out.println("successor 33333");
                if(a==1){remove(notDefined.getDataKey());  }
                return temp2.getLeftChild().getRightChild().getData();
            }
            System.out.println("No successor case triggered");
            if(a==1){remove(notDefined.getDataKey());  }
            return null;                                                                                                          //MAKE BETTER
            //if ((temp2.hasLeftChild()==true) && ())
            //or
            //or null
    }

    /* Returns the predecessor of k (the record from the ordered dictionary 
       with largest key smaller than k; It throws a DictionaryException says:
       "There is no predecessor for the given record key", if the given key has 
       no predecessor. The given key DOES NOT need to be in the dictionary.  
     
       @param k
       @return BirdRecord
       @throws DictionaryException
     */
    public BirdRecord predecessor(DataKey k) throws DictionaryException{
        //predecessor    
        BinaryNode<BirdRecord> temp = new BinaryNode<BirdRecord>();
            BinaryNode<BirdRecord> temp2 = new BinaryNode<BirdRecord>();
            temp.setLeftChild(root);
            
            BirdRecord notDefined = new BirdRecord(k,"","","");
            int a=0;
            
            try{ 
                find(k);
            }catch(DictionaryException ex){
                a=1;
                insert(notDefined);                
            }
            
            boolean found = false;
            //change logic
            while(  !found /* finish    /*temp.getLeftChild()!=*/){
                    int position = k.compareTo(   temp.getLeftChild().getData().getDataKey()   );     //compare root to r's data
                    if (position == 0){
                        temp2.setLeftChild(temp.getLeftChild());       //found, returns that nodes birdRecord                                   //CHanged
                        found =true;
                        continue;
                    }
                    if (position <= -1){
                        if (temp.getLeftChild().hasLeftChild()==false){  
                            throw new DictionaryException("There is no record that matches the given key");                                        //CHANGE?
                            //throw new DictionaryException("Record is already in the Dictionary");
                        }
                        temp.setLeftChild(temp.getLeftChild().getLeftChild());
                        continue;
                    }
                    if (position >= 1){
                        //if (root.hasRightChild()==false){  root.setRightChild(inserted);   }
                        if (temp.getLeftChild().hasRightChild()==false){  
                            throw new DictionaryException("There is no record that matches the given key");                                         //CHANGE?
                        }   
                        temp.setLeftChild(temp.getLeftChild().getRightChild());
                        continue;
                    }           
            }
            //if (smallest().){throw new DictionaryException("Dictionary is empty");}
            //Node is found, get predecessor
            
            //get left / get parent from right
            //1
            if (temp2.getLeftChild().getData() ==  smallest()){
                throw new DictionaryException("There is no predecessor for the given record key"); 
            }
            //System.out.println("predecessor 0000");
            
            if ((temp2.getLeftChild().isLeaf())&&(temp2.getLeftChild().hasParent())){
                System.out.println("predecessor 2222");
                //Wild turkey
                if ((temp2.getLeftChild().hasParent())&& (temp2.getLeftChild().getParent().getRightChild()==temp2.getLeftChild())){
                           if(a==1){remove(notDefined.getDataKey());  }
                           return temp2.getLeftChild().getParent().getData();
                }
                 //2 //4 
                //changed
                if ((temp2.getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getParent().getLeftChild().getData().getDataKey())) == 0){    //left node to parent
                   //hit null pointer
                   found = true;
                   while (found){
                       if ((temp2.getLeftChild().hasParent())&& (temp2.getLeftChild().getParent().getLeftChild()==temp2.getLeftChild())){
                           temp2.setLeftChild(temp2.getLeftChild().getParent());
                       }
                       else
                           found = false;
                   }
                   /*while((temp2.getLeftChild().hasParent())&& ( (temp2.getLeftChild().getParent().getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getData().getDataKey()))==0 )){    //changed
                       temp2.setLeftChild(temp2.getLeftChild().getParent());
                   }*/
                   //System.out.println("should be right for Northern Wheater");
                   if(a==1){remove(notDefined.getDataKey());  }
                   return temp2.getLeftChild().getParent().getData();       //returns first right parent
                }
                //3 //5 //6 //8
                if ((temp2.getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getParent().getData().getDataKey())) == -1){     //right node to parent
                    if(a==1){remove(notDefined.getDataKey());  }
                    return temp2.getLeftChild().getParent().getData();       //returns bird record of parent     
                }
            }
            
            //For Magnificent Frigatebird
            
            
            if((temp2.getLeftChild().hasLeftChild()==false)&&(temp2.getLeftChild().hasParent()==true)){
                System.out.println("predecessor 1111");
                //changed
                if(temp2.getLeftChild().getParent().hasLeftChild()==true){
                    if (((temp2.getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getParent().getLeftChild().getData().getDataKey())) == 0)
                    &&(temp2.getLeftChild().hasRightChild()==true)){    //left node to parent
                        //hit null pointer
                        found = true;
                        while (found){
                            if ((temp2.getLeftChild().hasParent())&& (temp2.getLeftChild().getParent().getLeftChild()==temp2.getLeftChild())){
                                temp2.setLeftChild(temp2.getLeftChild().getParent());
                            }
                            else
                                  found = false;
                        }
                        /*while((temp2.getLeftChild().hasParent())&& ( (temp2.getLeftChild().getParent().getLeftChild().getData().getDataKey().compareTo(temp2.getLeftChild().getData().getDataKey()))==0 )){    //changed
                           temp2.setLeftChild(temp2.getLeftChild().getParent());
                        }*/
                        //System.out.println("should be right for Northern Wheater");
                        if(a==1){remove(notDefined.getDataKey());  }
                        return temp2.getLeftChild().getParent().getData();       //returns first right parent
                    }
                }
                temp2.setRightChild(  temp2.getLeftChild().getParent() );
                if(a==1){  remove(k);  
                    return temp2.getRightChild().getParent().getData();
                }   
                return temp2.getRightChild().getData();
            }
       
            //10  //12
            if (  (temp2.getLeftChild().getLeftChild().isLeaf()==false) && (temp2.getLeftChild().getLeftChild().hasRightChild()) ){    
                System.out.println("predecessor 4444");
                temp2.setLeftChild(temp2.getLeftChild().getLeftChild().getRightChild());
                //10
                if(temp2.getLeftChild().getRightChild()==null){
                    System.out.println("What Northen Cardinal should trigger");                     //changed
                    if(a==1){remove(notDefined.getDataKey());  }
                    return temp2.getLeftChild().getData();
                } 
                while(temp2.getLeftChild().hasRightChild()){
                    if(a==1){remove(notDefined.getDataKey());  }
                    temp2.setLeftChild(temp2.getLeftChild().getRightChild());
                }
                //11
                if(a==1){remove(notDefined.getDataKey());  }
                return temp2.getLeftChild().getData();
            }
            //15
            if(  (temp2.getLeftChild().getLeftChild().isLeaf()==false) && (temp2.getLeftChild().getLeftChild().hasRightChild())==false  ){
                System.out.println("predecessor 555");
                if(a==1){remove(notDefined.getDataKey());  }
                return temp2.getLeftChild().getLeftChild().getData();
            }   
               //9
            if ((temp2.hasLeftChild()==true)&&(temp2.getLeftChild().getLeftChild().isLeaf()==true)){    
                System.out.println("predecessor 33333");
                if(a==1){remove(notDefined.getDataKey());  }
                return temp2.getLeftChild().getLeftChild().getData();
            }
            System.out.println("No predescesor case triggered");
            if(a==1){remove(notDefined.getDataKey());  }
            return null;                                                                                                          //MAKE BETTER
            //if ((temp2.hasLeftChild()==true) && ())
            //or
            //or null
    }

    /* Returns the record with smallest key in the ordered dictionary. 
       It throws a DictionaryException says:"Dictionary is empty", if the 
       dictionary is empty.   

       @return BirdRecord
       @throws DictionaryExceptions
     */
    public BirdRecord smallest() throws DictionaryException{
            if (isEmpty()==true){throw new DictionaryException("Dictionary is empty");}
            BinaryNode<BirdRecord> temp = new BinaryNode<BirdRecord>();
            if (root.hasLeftChild() == true){                       //Just the root or not
                temp.setLeftChild(root.getLeftChild());
                while (  temp.getLeftChild().hasLeftChild()  ){     //              //Using temp as a storage holder. Like a node pointer
                    temp.setLeftChild(  temp.getLeftChild().getLeftChild()  );      //Temp's child becomes it's old child's left child.
                }
                return temp.getLeftChild().getData();       //Returns the most left child
                /*
                while (!isNil(node.left))
			node = node.left;
		return node;*/
            }
            else{
                return root.getData();                              //If just the root
            }
    }

    /* Returns the record with largest key in the ordered dictionary. 
       It throws a DictionaryException says:"Dictionary is empty", if the 
       dictionary is empty.  
       @return BirdRecord
       @throws DictionaryException
     */
    public BirdRecord largest() throws DictionaryException{
        
            if (isEmpty()==true){throw new DictionaryException("Dictionary is empty");}
            BinaryNode<BirdRecord> temp = new BinaryNode<BirdRecord>();
            if (root.hasRightChild() == true){                       //Just the root or not
                temp.setLeftChild(root.getRightChild());
                while (  temp.getLeftChild().hasRightChild()  ){     //              //Using temp as a storage holder. Like a node pointer
                    temp.setLeftChild(  temp.getLeftChild().getRightChild()  );      //Temp's child becomes it's old child's right child. (left child in temp is just storage)
                }
                return temp.getLeftChild().getData();       //Returns the most left child
                /*
                while (!isNil(node.left))
			node = node.left;
		return node;*/
            }
            else{
                return root.getData();                              //If just the root
            }
      
    }

    /* Returns true if the dictionary is empty, and true otherwise. 

       @return boolean
     */
    public boolean isEmpty(){
        if ((root.getData()==null)&&((root.hasLeftChild()==false)&&(root.hasRightChild()==false))){
            return true;
        }
        else
            return false;
    }
}
