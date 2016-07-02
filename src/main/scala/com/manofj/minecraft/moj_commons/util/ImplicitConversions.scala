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
      * 指定された関数での変換を試みる｡変換時にエラーが発生した場合は
      * 暗黙に定義されている [[com.manofj.minecraft.moj_commons.util.DefaultValue]] の値を返す
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
    def %%[ B, C >: B ]( converter: A => B )
                       ( implicit defaultValue: DefaultValue[ C ] ): C =
      try converter( any ) catch { case NonFatal( _ ) => defaultValue.get }

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

  /**
    * Booleanクラスを拡張､汎用メソッドを追加する
    */
  implicit class BooleanExtension( val flag: Boolean )
    extends AnyVal
  {
    import com.manofj.minecraft.moj_commons.util.ImplicitConversions.BooleanExtension.TernaryExtension

    /**
      * 擬似的な三項演算を提供する
      * {{{
      *   import com.manofj.minecraft.moj_commons.util.ImplicitConversions.BooleanExtension
      *
      *   assert( true ?> "foo" !> "bar" == "foo" )
      *   assert( false ?> "foo" !> "bar" == "bar" )
      * }}}
      */
    def ?>[ A ]( trueCase: => A ): TernaryExtension[ A ] = new TernaryExtension( flag, () => trueCase )

  }

  /**
    * [[com.manofj.minecraft.moj_commons.util.ImplicitConversions.BooleanExtension]] で使用される
    * クラス､関数､定数を定義する
    */
  object BooleanExtension {

    // 擬似三項演算の中間クラス
    class TernaryExtension[ A ]( val values: ( Boolean, () => A ) )
      extends AnyVal
    {
      def !>[ B >: A ]( falseCase: => B ): B = if ( values._1 ) values._2() else falseCase
    }

  }

}
