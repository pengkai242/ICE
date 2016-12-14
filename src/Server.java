    public class Server  extends Ice.Application
    {
    	@Override
        public  int run(String[] args)
        {
//            Ice.ObjectAdapter adapter = communicator().createObjectAdapter("Callback.Server");  
    		//直接指定endpoint
    		Ice.ObjectAdapter adapter = communicator().createObjectAdapterWithEndpoints("Callback.Server", "default -p 10000");  
            
    		adapter.add(new ChatSpaceI(), communicator().stringToIdentity("callback"));
            adapter.activate();
            communicator().waitForShutdown();
            return 0;
        }

        public static void main(String[] args)
        {
            Server app = new Server();
            int status = app.main("config.server",args);
            if (status != 0)
            {
                System.exit(status);
            }
        }
    }