package com.example.nanoserver.myHtServer;

import java.io.IOException;
import java.net.ServerSocket;

public interface ServerSocketFactory {
    ServerSocket create() throws IOException;
}
