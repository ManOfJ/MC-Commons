package com.manofj.minecraft.moj_commons.minecraft.inventory


import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack


/**
  * インベントリに関する暗黙の変換を提供する
  */
object ImplicitConversions {

  /**
    * [[net.minecraft.inventory.IInventory]] クラスを拡張､汎用メソッドを追加する
    */
  implicit class InventoryExtension( val inventory: IInventory )
    extends AnyVal
  {

    /**
      * 指定された範囲のスロットに格納されているアイテムをストリームとして返す
      */
    def toStream( begin: Int, end: Int ): Stream[ ItemStack ] =
      Stream.range( begin max 0, end min inventory.getSizeInventory ).map( inventory.getStackInSlot )

    /**
      * 自身に格納されているアイテムをストリームとして返す
      */
    def toStream: Stream[ ItemStack ] =
      Stream.range( 0, inventory.getSizeInventory ).map( inventory.getStackInSlot )

  }

}
