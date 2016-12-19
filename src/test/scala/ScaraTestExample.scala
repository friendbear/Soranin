package test.scala

import org.scalatest.FunSuite
/**
  * Created by kumagai on 2016/12/19.
  */
class ScaraTestExample {

}

class SetSuite extends FunSuite {

  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

}