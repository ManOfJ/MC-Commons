package com.manofj.minecraft.moj_commons.collection


package object java {

  /**
    * Javaのコレクションクラスに対するエイリアスを定義する
    */
  object alias {

    type JavaList[ A ] = _root_.java.util.List[ A ]

    type JavaSet[ A ] = _root_.java.util.Set[ A ]

    type JavaMap[ A, B ] = _root_.java.util.Map[ A, B ]

  }

}
