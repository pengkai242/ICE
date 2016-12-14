import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ChatSpaceDef.CallBackPrx;
import ChatSpaceDef.CallBackPrxHelper;
import ChatSpaceDef.ChatSpacePrx;
import ChatSpaceDef.ChatSpacePrxHelper;

public class Client extends Ice.Application {
	public static void main(String[] args) {
		Client app = new Client();
		int status = app.main("Client", args, "config.client");
		if (status != 0) {
			System.exit(status);
		}
	}

	@Override
	public int run(String[] args) {
	/*	ChatSpacePrx spacePrx = ChatSpacePrxHelper.checkedCast(communicator()
				.propertyToProxy("Callback.CallbackServer"));*/
		//指定endpoint
		ChatSpacePrx spacePrx = ChatSpacePrxHelper.checkedCast(communicator()
				.stringToProxy("callback:default -p 10000"));

		if (spacePrx == null) {
			System.out.println("网络配置无效!");
			return 1;
		}
		System.out.println("请输入用户名：\r\n");
		java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String user_name = "";
		try {
			user_name = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (spacePrx.Register(user_name) == false) {
			System.out.println("该用户名已被注册!");
			return 1;
		}
		Ice.ObjectAdapter adapter = communicator().createObjectAdapter(
				"Callback.Client");
		adapter.add(new CallBackI(),
				communicator().stringToIdentity("callbackReceiver"));
		adapter.activate();

		CallBackPrx callbackPrx = CallBackPrxHelper.uncheckedCast(adapter
				.createProxy(communicator()
						.stringToIdentity("callbackReceiver")));
//		Ice.Current current = new Ice.Current();
//		Map ctx = current.ctx;
		Map ctx = new HashMap<String,String>();
		ctx.put("user_name", user_name);

		spacePrx.SetupCallback(callbackPrx, ctx);

		System.out.println("请输入聊天内容：\r\n");
		String content = "";
		while (content != "X") {
			try {
				content = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			spacePrx.SetInput(content, ctx);
		}
		spacePrx.Unregister(ctx);

		return 0;

	}
}
