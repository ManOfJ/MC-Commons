package com.manofj.minecraft.moj_commons.util


/**
  * MinecraftのModであることをあらわす
  * Modの基本情報を取得するメソッドを持つ
  */
trait MinecraftMod {

  def modId: String

  def modName: String

  def modVersion: String

}
