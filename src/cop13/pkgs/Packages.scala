package cop13.pkgs

/** パッケージの階層とアクセス
  * Created by kumagai on 2016/12/18.
  */

package launch {

  class Booster3

}

package bobsrockets {
  package navigation {
    package launch {
      class Booster1
    }
    class MissionControl {
      val booster1 = new launch.Booster1
      val booster2 = new bobsrockets.launch.Booster2
      val booster3 = new _root_.cop13.pkgs.launch.Booster3
    }
  }
  package launch {
    class Booster2
  }
}


