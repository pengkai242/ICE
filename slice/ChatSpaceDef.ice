module ChatSpaceDef
{
       //�ص������ӿڣ����ǿͻ��˴��ݸ������������������յ���һ����������ǩ��
       interface CallBack
       {
              void GetInput(string content);
       };
       //�����б�
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

