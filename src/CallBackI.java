import ChatSpaceDef._CallBackDisp;

    public  class CallBackI extends _CallBackDisp
    {
    	@Override
        public  void GetInput(String content, Ice.Current current__)
        {
            System.out.println("���������ݹ�������Ϣ��"+current__.ctx.get("user_name")+" Say:"+content);
        }
    }