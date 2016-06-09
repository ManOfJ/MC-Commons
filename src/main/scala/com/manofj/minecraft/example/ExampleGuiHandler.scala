package com.manofj.minecraft.example


import com.manofj.minecraft.moj_commons.config.ConfigGuiHandler


object ExampleGuiHandler
  extends ConfigGuiHandler
{
  override def modId: String = ExampleMod.ID

  override def title: String = s"${ ExampleMod.NAME } - Config GUI"

  override def syncConfig( load: Boolean ): Unit = {
    // do something ...
  }
}
