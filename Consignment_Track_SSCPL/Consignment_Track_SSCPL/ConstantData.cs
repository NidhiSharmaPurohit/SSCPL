using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Consignment_Track_SSCPL
{
    public class ConstantData
    {
        public static List<String> GetStates()
        {
            List <String> StatesAndUnionTeriorities= new List<String>();
            if(File.Exists("States.json"))
            {
                StatesAndUnionTeriorities =  JsonConvert.DeserializeObject< List<String>>(File.ReadAllText("States.json"));
            }
            return StatesAndUnionTeriorities;


        }



    }

    public class Teriority
    {
        public List<string> States { get; set; }
    }
}
