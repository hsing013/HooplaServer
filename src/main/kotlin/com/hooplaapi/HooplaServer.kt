package com.hooplaapi

import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.ServerSocket


class HooplaServer{
    private var serverSocket : ServerSocket
    private var DEFAULT_PORT = 5000
    private var DEFAULT_BACKLOG = 1000
    private var QUIT_FLAG = false
    private var identifierGenerator = IdentifierGenerator()
    private var clientManager = ClientManager()

    constructor(port : Int) {
        this.DEFAULT_PORT = port
        this.serverSocket = ServerSocket()
        setupSocket()
    }

    constructor(){
        this.serverSocket = ServerSocket()
        setupSocket()
    }


    private fun setupSocket(){
        this.clientManager.start()
        val inetSocketAddress = InetSocketAddress(InetAddress.getLocalHost(), DEFAULT_PORT);
        serverSocket.bind(inetSocketAddress, DEFAULT_BACKLOG);
    }

    fun startListening(){
        QUIT_FLAG = false;
        while (!QUIT_FLAG){
            //println(serverSocket.localSocketAddress)
            val clientSocket = this.serverSocket.accept();
            //println("recieved connection")
            val identifier = identifierGenerator.generateIdentifier()
            val client = HooplaClient(clientSocket, identifier, clientManager)
            clientManager.insertIntoHashMap(identifier, client)
            client.start()
        }
    }

    private fun cleanUp(){
        //TODO: Cleanup after server has stop running
    }



}