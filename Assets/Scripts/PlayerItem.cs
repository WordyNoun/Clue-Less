using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using Photon.Pun;
using Photon.Realtime;
using TMPro;

public class PlayerItem : MonoBehaviour
{

    public TMP_Text playerName;

    public void SetPlayerInfo(Player player)
    {
        playerName.text = player.NickName;
    }
}
