package com.example.ratha.realtimedb.service

import com.example.ratha.realtimedb.model.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object ChatService {

    const val ROOT_NOED="chat"
    const val MESSAGE_NODE="messages"
    const val USER_NODE="users"
    const val CHAT_NODE="user-chat"
    var MESSAGE_NODE_KEY : String?= ""
    var USER_NODE_KEY : String?= ""
    val database= FirebaseDatabase.getInstance()

    fun initFirebaseDatabase(rootNode: String): DatabaseReference=
            database.getReference(rootNode)

    fun getDatabaseReference( node: String): DatabaseReference= database.getReference(node)

    fun writeUser(ref :DatabaseReference, user : User){
        USER_NODE_KEY= ref.child(USER_NODE).push().key ?: return
        user.uid= USER_NODE_KEY
        val userValue=user.toMap()
        val childUpdates=HashMap<String,Any>()
        childUpdates["/$USER_NODE/$USER_NODE_KEY"]=userValue
        ref.updateChildren(childUpdates)

    }


}