package com.manofj.minecraft.moj_commons


import scala.language.implicitConversions

import com.manofj.minecraft.moj_commons.io.java.alias.JavaFile


package object config {

  // Minecraft Forgeのコンフィグ関連クラスの型エイリアス
  type ForgeConfig   = _root_.net.minecraftforge.common.config.Configuration
  type ForgeCategory = _root_.net.minecraftforge.common.config.ConfigCategory
  type ForgeProperty = _root_.net.minecraftforge.common.config.Property


  final val DEFAULT_CONFIG_ID = _root_.net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL


  implicit def javaFile2ForgeConfig( file: JavaFile ): ForgeConfig = new ForgeConfig( file )

}
