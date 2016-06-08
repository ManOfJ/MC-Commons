package com.manofj.minecraft.moj_commons.util


import scala.util.control.NonFatal


/**
  * 汎用的な暗黙の変換を提供する
  */
object ImplicitConversions {

  /**
    * すべてのクラスを拡張､汎用メソッドを追加する
    */
  implicit class AnyExtension[ A <: Any ]( val any: A )
    extends AnyVal
  {

    /**
      * 指定された初期化ブロックを実行後､自身を返す
      * {{{
      *   import java.sql.{ Connection, DriverManager }
      *   import com.manofj.minecraft.moj_commons.util.ImplicitConversions.AnyExtension
      *
      *   val connection = DriverManager.getConnection( "url" ) << { _.setAutoCommit( false ) }
      *   assert( connection.isInstanceOf[ Connection ] )
      * }}}
      */
    def <<( initializer: A => Unit ): A = { initializer( any ); any }

    /**
      *
      * {{{
      *   import com.manofj.minecraft.moj_commons.util.DefaultValue.Implicits.IntDefault
      *   import com.manofj.minecraft.moj_commons.util.ImplicitConversions.AnyExtension
      *
      *   val number1 = "0601" %% { _.toInt }
      *   assert( number1 == 601 )
      *
      *   val number2 = "Not Number" %% { _.toInt }
      *   assert( number2 == 0 )
      * }}}
      */
    def %%[ B ]( converter: A => B )
               ( implicit defaultValue: DefaultValue[ B ] ): B =
      try { converter( any ) } catch { case NonFatal( _ ) => DefaultValue[ B ] }

    /**
      * 自身を [[scala.Option]] でラップしたものを返す
      * {{{
      *   import com.manofj.minecraft.moj_commons.util.ImplicitConversions.AnyExtension
      *
      *   def parseInt( string: String ): Int = {
      *     string.? match {
      *       case None      => -1
      *       case Some( v ) => v.toInt
      *     }
      *   }
      *   assert( parseInt( "0601" ) == 601 )
      *   assert( parseInt( null ) == -1 )
      * }}}
      */
    def ? : Option[ A ] = Option( any )

    /**
      * 自身が `null` であればデフォルト値を､そうでなければ自身を返す
      */
    def ?/[ B >: A ]( default: B ): B = Option( any ).getOrElse( default )

  }

}
