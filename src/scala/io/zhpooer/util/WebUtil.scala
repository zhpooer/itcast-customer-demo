package io.zhpooer.util

import javax.servlet.http.HttpServletRequest
import org.apache.commons.beanutils.BeanUtils

object WebUtil {
  def fillBean[T](req:HttpServletRequest, clazz:Class[T]):T = {
    val bean:T= clazz.newInstance()
    BeanUtils.populate(bean, req.getParameterMap().asInstanceOf[java.util.Map[String, Object]])
    bean 
  }
}