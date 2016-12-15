package cop09

/**
  * Created by kumagai on 2016/12/15.
  */
class ClientSimple {

  def containsNeg(nums: List[Int]): Boolean = {
    var exists = false
    for (num <- nums)
      if (num < 0) exists = true
    exists
  }

  def containsNeg2(nums: List[Int]): Boolean = nums.exists(_ < 0)

  def containsOdd(nums: List[Int]): Boolean = nums.exists(_ % 2 == 1)
}
