package com.github.simplyscala

import org.mongodb.scala.bson.collection.immutable.Document
import org.scalatest.BeforeAndAfter
import Helpers._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class MongoEmbedDatabaseTest extends AnyFunSuite with Matchers with BeforeAndAfter with MongoEmbedDatabase {

    var mongoProps: MongodProps = null

    before { mongoProps = mongoStart(33333) }

    after { mongoStop(mongoProps) }

    test("test connection with embed mongodb") {
      val conn = Connector.getCollection(Connector.conf2)
      conn.insertOne(document = Document("name" -> "testFixture")).results()
      conn.countDocuments().headResult() should be (1)
    }
}
