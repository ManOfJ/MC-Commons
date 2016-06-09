package com.manofj.minecraft.moj_commons.config


import scala.reflect.ClassTag

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen

import net.minecraftforge.fml.client.IModGuiFactory
import net.minecraftforge.fml.client.IModGuiFactory.{ RuntimeOptionCategoryElement, RuntimeOptionGuiHandler }

import com.manofj.minecraft.moj_commons.collection.java.alias.JavaSet


/**
  * シンプルなコンフィグGUIファクトリ
  */
abstract class ConfigGuiFactory[ A <: ConfigGui : ClassTag ]
  extends IModGuiFactory
{
  override def runtimeGuiCategories(): JavaSet[ RuntimeOptionCategoryElement ] = null
  override def initialize( minecraftInstance: Minecraft ): Unit = {}
  override def getHandlerFor( element: RuntimeOptionCategoryElement ): RuntimeOptionGuiHandler = null

  override def mainConfigGuiClass(): Class[ _ <: GuiScreen ] =
    implicitly[ ClassTag[ A ] ].runtimeClass.asInstanceOf[ Class[ A ] ]
}
