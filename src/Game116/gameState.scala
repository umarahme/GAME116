package Game116

import scalafx.scene.Group

class gameState {
  //I think this is where we should sotre the state of the game, so all lists and they would be changed and edited through here
  //We could set a variable equal to gameState and have all its properties that need to be displayed, displayed on the GUI like in the platformer
  var sceneGraphics: Group = new Group {}
  val world: World = new World

}
