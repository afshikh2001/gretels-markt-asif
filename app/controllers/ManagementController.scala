package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

class ManagementController  @Inject()(cc: ControllerComponents) (implicit ec: ExecutionContext) extends AbstractController(cc){

  def deleteUser()=Action{implicit request=>
    Ok("")
  }

  def cancelOrder()=Action{implicit request=>
    Ok("")
  }

  def showAllOrders()=Action{implicit request=>
    Ok("")
  }

  def showCompleteOrders()=Action{implicit request=>
    Ok("")
  }

  def showNewOrders()=Action{implicit request=>
    Ok("")
  }

  def showInProgressOrders()=Action{implicit request=>
    Ok("")
  }

  def addProduct()=Action{implicit request=>
    Ok("")
  }

  def deleteProduct()=Action{implicit request=>
    Ok("")
  }






}
