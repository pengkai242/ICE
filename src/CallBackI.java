import ChatSpaceDef._CallBackDisp;

    public  class CallBackI extends _CallBackDisp
    {
    	@Override
        public  void GetInput(String content, Ice.Current current__)
        {
            System.out.println("服务器传递过来的信息："+current__.ctx.get("user_name")+" Say:"+content);
        }
    }