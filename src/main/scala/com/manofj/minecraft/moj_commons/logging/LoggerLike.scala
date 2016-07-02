package com.manofj.minecraft.moj_commons.logging


import org.apache.logging.log4j.Logger


/**
  * 自身がロガーのようにふるまえることを表す
  * オーソドックスなログ出力関数を持つ
  */
trait LoggerLike {

  /** 実際にログ出力を行うロガー */
  protected def logger: Logger


  // ロガーへの委譲メソッド群
  def debug( message: String ): Unit = logger.debug( message )
  def debug( message: String, params: AnyRef* ): Unit = logger.debug( message, params )
  def debug( message: String, t: Throwable ): Unit = logger.debug( message, t )
  def error( message: String ): Unit = logger.error( message )
  def error( message: String, params: AnyRef* ): Unit = logger.error( message, params )
  def error( message: String, t: Throwable ): Unit = logger.error( message, t )
  def fatal( message: String ): Unit = logger.fatal( message )
  def fatal( message: String, params: AnyRef* ): Unit = logger.fatal( message, params )
  def fatal( message: String, t: Throwable ): Unit = logger.fatal( message, t )
  def info( message: String ): Unit = logger.info( message )
  def info( message: String, params: AnyRef* ): Unit = logger.info( message, params )
  def info( message: String, t: Throwable ): Unit = logger.info( message, t )
  def trace( message: String ): Unit = logger.trace( message )
  def trace( message: String, params: AnyRef* ): Unit = logger.trace( message, params )
  def trace( message: String, t: Throwable ): Unit = logger.trace( message, t )
  def warn( message: String ): Unit = logger.warn( message )
  def warn( message: String, params: AnyRef* ): Unit = logger.warn( message, params )
  def warn( message: String, t: Throwable ): Unit = logger.warn( message, t )

}
