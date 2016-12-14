import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import ChatSpaceDef.CallBackPrx;
import ChatSpaceDef._ChatSpaceDisp;

public class ChatSpaceI extends _ChatSpaceDisp {
	Map<String ,CallBackPrx> m_cache_map = new HashMap<String ,CallBackPrx>();
	@Override
	public boolean Register(String name, Ice.Current current__) {
		System.out.println("��֤ "+name+" �Ƿ�ע����!:");
		return !m_cache_map.containsKey(name);
	}

	@Override
        public  void SetInput(String content, Ice.Current current__)
        {
 
            String user_name = current__.ctx.get("user_name");

            for (String key  : m_cache_map.keySet())
            {
                if (key.equals( user_name))
                {
                    continue;
                }
                m_cache_map.get(key).GetInput(content, current__.ctx);
            }
        }

	@Override
	public void Unregister(Ice.Current current__) {
		if ( current__.ctx.get("user_name") != null) {
			m_cache_map.remove( current__.ctx.get("user_name"));
			System.out.println("ע��"+ current__.ctx.get("user_name")+"�ɹ�");
		}

	}

	@Override
	public void SetupCallback(CallBackPrx cp, Ice.Current current__)  {
		if (current__ != null &&  current__.ctx.get("user_name") != null) {
			String user_name =  current__.ctx.get("user_name");
			System.out.println("��������user_name="+ user_name);
			m_cache_map.put(user_name, cp);
		} else {
			System.out.println("�����Ĵ���!");
		}

	}

	

}