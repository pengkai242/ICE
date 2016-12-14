module ChatSpaceDef
{
       //回调函数接口，就是客户端传递给服务器，服务器接收到的一个方法代理签名
       interface CallBack
       {
              void GetInput(string content);
       };
       //在线列表
       dictionary<string,CallBack *> CacheMap;
       //
       interface ChatSpace
       {
              bool Register(string name);
              void SetInput(string content);
              void Unregister();
              void SetupCallback(CallBack * cp);
       };

};

