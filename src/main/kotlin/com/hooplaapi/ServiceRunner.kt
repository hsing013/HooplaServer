package com.hooplaapi

import com.google.common.collect.Lists
import com.mongodb.MongoClient
import org.bson.Document


fun main(args : Array<String>){
//   var hooplaServer = HooplaServer(5001)
//   hooplaServer.startListening()
//   try {
//      var comparator = DummyComparator()
//      var queue = PriorityQueue<DummyClass>(1, Collections.reverseOrder(comparator))
//      queue.add(DummyClass(-1))
//      var gurtaj = DummyClass(65)
//      gurtaj.someBoolean.set(false)
//      queue.add(gurtaj)
//      queue.add(DummyClass(4))
//      //gurtaj.someBoolean.set(true)
//      println("Hoopla")
//      queue.add(DummyClass(2))
//
//
//
//      exitProcess(0)
//      var someYa = GetUserProfileJob(Document())
//      var k : Document = someYa.get()
//      println("Things are being changed");
      var mongoClient = MongoClient("localhost", 27017)
      var list = mongoClient.listDatabaseNames()
      var arraylist = Lists.newArrayList(list)
      var database = mongoClient.getDatabase("local")
      var collection = database.getCollection("Person")
      var persons = ArrayList<Document>()
      for (i in 112..113){
         //val person = BasicDBObject("_id", i).append("name", "RUMHAM")
         //val p = Document().append("_id", i).append("name", "RUMHAM")
         //persons.add(p)
      }
      val p = Document().append("name", "RUMHAM").append("_id", -987654)
      persons.add(p)

      for (doc in persons){
         collection.insertOne(doc)
      }

//      var collection2 = database.getCollection("Person2")
//      val books = Arrays.asList(27464, 747854)
////   val person = BasicDBObject("_id", "Snape")
////      .append("name", "Jo Bloggs")
////      .append(
////         "address", BasicDBObject("street", "123 Fake St")
////            .append("city", "Hoopla")
////            .append("state", "MA")
////            .append("zip", 12345)
////      )
////      .append("books", books)
////   collection.insertOne(Document(person.toMap() as MutableMap<String, Any>?))
//      var query = Document()
//      query.append("_id", "jo")
//      var setDoc = Document()
//      setDoc.append("address.city", "Hoopla")
//      setDoc.append("address.message", "Hello world")
//      var update = Document()
//      update.append("\$set", setDoc)
//      collection.updateOne(query, update)
//
//      var find = Document()
//      find.append("_id", "jo") //find
//      var find2 = Document().append("address", 1) //filter by
//      var time = System.currentTimeMillis()
//      var cursor = collection.find(eq("_id", "jo")).projection(include("address")).first()
//      var findMe = Document()
//      findMe.append("_id", "jo")
//      var getSizeOf  = collection.find(findMe).first()
//      println(System.currentTimeMillis() - time)
//      //println(cursor.toString())
//      println("Hold your horses")
//      var listOfStrings = ArrayList<String>()
//      for (i in 0..100000) {
//         listOfStrings.add("Lily is crazy")
//      }
//
//      setDoc = Document()
//      update = Document()
//
//      setDoc.append("address.ListOfNames", listOfStrings)
//      update.append("\$set", setDoc)
//      var startTime = System.currentTimeMillis()
//      collection.updateOne(query, update)
//      startTime = System.currentTimeMillis() - startTime
//
//      print("It took this long to save a million $startTime")
//      var future = CompletableFuture.completedFuture("Hoopla")
//
//   }
//   catch (e : Exception){
//      println(e)
//   }
}



