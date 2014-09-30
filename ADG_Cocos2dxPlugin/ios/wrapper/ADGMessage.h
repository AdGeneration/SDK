typedef void (*FUNC)(const char * , const char *);

extern "C"{
    void sendNativeMessage(const char *msg , const char *adid , FUNC func);
}