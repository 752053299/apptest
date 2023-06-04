package com.example.nanoserver.myHtServer.clentDispatch;



public interface IAsyncRunner {
    void closeAll();
    void closeClient(AsyncRunner.ClientHandler clientHandler);
    void runAClient(AsyncRunner.ClientHandler clientHandler);
}
