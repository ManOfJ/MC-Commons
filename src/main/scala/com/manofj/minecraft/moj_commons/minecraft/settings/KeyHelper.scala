package com.manofj.minecraft.moj_commons.minecraft.settings


import java.util.Locale

import org.lwjgl.input.Keyboard

import net.minecraftforge.client.settings.KeyModifier

import com.manofj.minecraft.moj_commons.util.ImplicitConversions.BooleanExtension


/**
  * キーバインドに関するユーティリティ関数を定義する
  */
object KeyHelper {

  // キーのインデックス範囲
  private[ this ] final val INDEX_BOUNDS = Range( 0, Keyboard.KEYBOARD_SIZE )


  /**
    * 指定された名前を持つキーのインデックスを返す
    * 該当するキーがない場合は [[org.lwjgl.input.Keyboard#KEY_NONE]] を返す
    */
  def keyIndex( keyName: String ): Int = Keyboard.getKeyIndex( keyName.toUpperCase( Locale.ENGLISH ) )

  /**
    * 指定されたインデックスを持つキーの名前を返す
    * 該当するキーがない場合は文字列 `"NONE"` を返す
    */
  def keyName( index: Int ): String =
    INDEX_BOUNDS.contains( index ) ?> Keyboard.getKeyName( index ) !> Keyboard.getKeyName( Keyboard.KEY_NONE )

  /**
    * 指定された名前に相当する修飾キーを返す
    * 該当するキーがない場合は [[net.minecraftforge.client.settings.KeyModifier#NONE]] を返す
    */
  def keyModifier( modifierName: String ): KeyModifier =
    modifierName.toUpperCase( Locale.ENGLISH ) match {
      case "CTRL" | "CONTROL" => KeyModifier.CONTROL
      case "ALT" => KeyModifier.ALT
      case "SHIFT" => KeyModifier.SHIFT
      case _ => KeyModifier.NONE
  }

}
