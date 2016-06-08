package com.manofj.minecraft.moj_commons.util


/**
  * 暗黙に定義されたデフォルト値を取得するメソッドと
  * このライブラリにより提供される、デフォルト値の提供オブジェクト群を持つ
  */
object DefaultValue {

  /**
    * スタンダードな型のデフォルト値を定義する
    */
  object Implicits {

    implicit object ByteDefault extends DefaultValue[ Byte ] { override val get: Byte = 0 }
    implicit object ShortDefault extends DefaultValue[ Short ] { override val get: Short = 0 }
    implicit object IntDefault extends DefaultValue[ Int ] { override val get: Int = 0 }
    implicit object LongDefault extends DefaultValue[ Long ] { override val get: Long = 0 }
    implicit object FloatDefault extends DefaultValue[ Float ] { override val get: Float = 0 }
    implicit object DoubleDefault extends DefaultValue[ Double ] { override val get: Double = 0 }
    implicit object BooleanDefault extends DefaultValue[ Boolean ] { override val get: Boolean = false }
    implicit object CharDefault extends DefaultValue[ Char ] { override val get: Char = '\u0000' }
    implicit object StringDefault extends DefaultValue[ String ] { override val get: String = "" }

  }

  /**
    * 指定された型パラメータの初期値を取得する
    * このメソッドを動作させるため､スコープ内に指定の型パラメータを持つ
    * [[com.manofj.minecraft.moj_commons.util.DefaultValue]] が定義されてる必要がある
    * {{{
    *   import com.manofj.minecraft.moj_commons.util.DefaultValue
    *   import com.manofj.minecraft.moj_commons.util.DefaultValue.Implicits.IntDefault
    *
    *   val number = try { "Not Number".toInt }
    *                catch { case e: NumberFormatException =>
    *                  DefaultValue[ Int ]
    *                }
    *   assert( number == 0 )
    * }}}
    */
  def apply[ A ]( implicit defaultValue: DefaultValue[ A ] ): A = defaultValue.get

}

/** デフォルト値を提供するメソッドを持つ */
trait DefaultValue[ A ] { def get: A }
