cmake_minimum_required(VERSION 3.17)
project(TP3 C)

set(CMAKE_C_STANDARD 11)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -pthread")



include_directories(.)

add_executable(TP3
        main.c)
