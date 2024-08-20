package com.test.ui

import com.test.chatapp.core.ui.R
import com.test.navigation.ChatListDestination
import com.test.navigation.ChatTopLevelDestination
import com.test.navigation.DetailsDestination
import com.test.navigation.KiparoTopLevelDestination
import com.test.navigation.MENU_GRAPH


data class MenuSection(
   val section: String,
   val image: String,
)

data class MenuItem(
   val section: MenuSection,
   val id: String,
   val title: String,
   val description: String,
   val image: String,
   val price: String
) {
   companion object
}


data class MenuTopLevelDestination(
   override val iconId: Int = R.drawable.ic_home,
   override val titleId: Int = R.string.menu,
   override val graph: String = MENU_GRAPH
) : KiparoTopLevelDestination

object BottomState {
   val chatMenu = arrayListOf<ChatTopLevelDestination>()
   val chatBottomMenu = arrayListOf<MenuItem>()

   fun addBottomItems(){
      chatBottomMenu.add(MenuItem(
         MenuSection("", ""),
         "0", "", "", "", ""
      ))

      chatBottomMenu.add(MenuItem(
         MenuSection("", ""),
         "0", "", "", "", ""
      ))

      chatBottomMenu.add(MenuItem(
         MenuSection("", ""),
         "0", "", "", "", ""
      ))
   }

   fun addChatList(){
      chatMenu.add(object : ChatTopLevelDestination{
         override val graph: String
            get() = ChatListDestination.route
         override val iconId: Int
            get() = R.drawable.ic_home
         override val titleId: Int
            get() = R.string.get_started

      })
   }
   fun addProfile(){
      chatMenu.add(object : ChatTopLevelDestination{
         override val graph: String
            get() = DetailsDestination.route
         override val iconId: Int
            get() = com.test.chatapp.core.ui.R.drawable.ic_profile
         override val titleId: Int
            get() = R.string.get_started

      })
   }

}