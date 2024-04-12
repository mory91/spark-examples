// scalastyle:off println
package org.apache.spark.examples.mllib

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
// $example on$
import org.apache.spark.mllib.feature.{Word2Vec, Word2VecModel}
// $example off$

object Word2VecExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Word2VecExample")
    val sc = new SparkContext(conf)

    // $example on$
    val input = sc.textFile("s3://morteza-files-ca/data/word2vec/input.txt").map(line => line.split(" ").toSeq)


    val word2vec = new Word2Vec()
    word2vec.setNumIterations(1000)
    word2vec.setVectorSize(100)
    // word2vec.setWindowSize(5)
    val model = word2vec.fit(input)
    



    println("Done")
    val synonyms = model.findSynonyms("sometime", 5)

    for((synonym, cosineSimilarity) <- synonyms) {
      println(s"$synonym $cosineSimilarity")
    }

    sc.stop()
  }
}
// scalastyle:on println
