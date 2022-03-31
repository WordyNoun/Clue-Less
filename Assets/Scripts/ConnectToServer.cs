using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Photon.Pun;
using UnityEngine.SceneManagement;
using UnityEngine.UI;
using TMPro;

public class ConnectToServer : MonoBehaviourPunCallbacks
{

    public TMP_InputField usernameInput;
    public TMP_Text buttonText;

    public void ServerConnect()
    {
        if(usernameInput.text.Length > 0)
        {
            PhotonNetwork.NickName = usernameInput.text;
            buttonText.text = "Connecting...";
            PhotonNetwork.ConnectUsingSettings();
        }
    }
    
    public override void OnConnectedToMaster()
    {
        SceneManager.LoadScene("Lobby Scene");
    }
}
