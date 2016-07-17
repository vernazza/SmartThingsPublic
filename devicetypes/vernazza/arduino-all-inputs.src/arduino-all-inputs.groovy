/**
 *  My First XBee Device
 *
 *  Copyright 2015 Paolo Vernazza
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "Arduino - All Inputs", namespace: "vernazza", author: "Paolo Vernazza") {
	capability "Switch"
        capability "Actuator"
        capability "Configuration"
        capability "Refresh"
        capability "Button"

        fingerprint endpointId: "03", profileId: "0104", inClusters: "0000,0003",  outClusters: "0003,0006"
        fingerprint endpointId: "04", profileId: "0104", inClusters: "0000,0003",  outClusters: "0003,0006"
        fingerprint endpointId: "05", profileId: "0104", inClusters: "0000,0003",  outClusters: "0003,0006"
        fingerprint endpointId: "06", profileId: "0104", inClusters: "0000,0003",  outClusters: "0003,0006"
        fingerprint endpointId: "07", profileId: "0104", inClusters: "0000,0003",  outClusters: "0003,0006"
        fingerprint endpointId: "08", profileId: "0104", inClusters: "0000,0003",  outClusters: "0003,0006"
        fingerprint endpointId: "09", profileId: "0104", inClusters: "0000,0003",  outClusters: "0003,0006"
        fingerprint endpointId: "10", profileId: "0104", inClusters: "0000,0003",  outClusters: "0003,0006"
	}

	simulator {
		status "on":"on/off: 1"
		status "off":"on/off: 0"
        
                reply "zcl on-off on":"on/off: 1"
                reply "zcl on-off off":"on/off: 0"
	}
  
        standardTile("button3", "button3", width: 1, height: 1, canChangeIcon: false)
        {
            state "default", label: 'libero', icon: "st.secondary.off", backgroundColor:"#79b821"
			state "pushed", label: 'premuto', icon: "st.indicators.lit-when-on", backgroundColor: "#FFA81E"
			state "held", label: 'tenuto', icon: "st.indicators.lit-when-on", backgroundColor: "#0066ff"
        }
        standardTile("button4", "button4", width: 1, height: 1, canChangeIcon: false)
        {
            state "default", label: 'libero', icon: "st.secondary.off", backgroundColor:"#79b821"
			state "pushed", label: 'premuto', icon: "st.indicators.lit-when-on", backgroundColor: "#FFA81E"
			state "held", label: 'tenuto', icon: "st.indicators.lit-when-on", backgroundColor: "#0066ff"
        }
        standardTile("button5", "button5", width: 1, height: 1, canChangeIcon: false)
        {
            state "default", label: 'libero', icon: "st.secondary.off", backgroundColor:"#79b821"
			state "pushed", label: 'premuto', icon: "st.indicators.lit-when-on", backgroundColor: "#FFA81E"
			state "held", label: 'tenuto', icon: "st.indicators.lit-when-on", backgroundColor: "#0066ff"
        }
        standardTile("button6", "button6", width: 1, height: 1, canChangeIcon: false)
        {
            state "default", label: 'libero', icon: "st.secondary.off", backgroundColor:"#79b821"
			state "pushed", label: 'premuto', icon: "st.indicators.lit-when-on", backgroundColor: "#FFA81E"
			state "held", label: 'tenuto', icon: "st.indicators.lit-when-on", backgroundColor: "#0066ff"
        }
        standardTile("button7", "button7", width: 1, height: 1, canChangeIcon: false)
        {
            state "default", label: 'libero', icon: "st.secondary.off", backgroundColor:"#79b821"
			state "pushed", label: 'premuto', icon: "st.indicators.lit-when-on", backgroundColor: "#FFA81E"
			state "held", label: 'tenuto', icon: "st.indicators.lit-when-on", backgroundColor: "#0066ff"
        }
        standardTile("button8", "button8", width: 1, height: 1, canChangeIcon: false)
        {
            state "default", label: 'libero', icon: "st.secondary.off", backgroundColor:"#79b821"
			state "pushed", label: 'premuto', icon: "st.indicators.lit-when-on", backgroundColor: "#FFA81E"
			state "held", label: 'tenuto', icon: "st.indicators.lit-when-on", backgroundColor: "#0066ff"
        } 	
        standardTile("button9", "button9", width: 1, height: 1, canChangeIcon: false)
        {
            state "default", label: 'libero', icon: "st.secondary.off", backgroundColor:"#79b821"
			state "pushed", label: 'premuto', icon: "st.indicators.lit-when-on", backgroundColor: "#FFA81E"
			state "held", label: 'tenuto', icon: "st.indicators.lit-when-on", backgroundColor: "#0066ff"
        }
        standardTile("button10", "button10", width: 1, height: 1, canChangeIcon: false)
        {
            state "default", label: 'libero', icon: "st.secondary.off", backgroundColor:"#79b821"
			state "pushed", label: 'premuto', icon: "st.indicators.lit-when-on", backgroundColor: "#FFA81E"
			state "held", label: 'tenuto', icon: "st.indicators.lit-when-on", backgroundColor: "#0066ff"
        }
        standardTile("refresh", "device.refresh", width: 1, height: 1, inactiveLabel: false) {
			state "default", action:"refresh.refresh", icon:"st.secondary.refresh"
        }
        
        main (["button3"])
        details(["button3", "button4", "button5", "button6", "button7", "button8", "button9", "button10", "refresh"])
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
 
    def result = []
    
    state.heartbeat = Calendar.getInstance().getTimeInMillis()
	
    if (description?.startsWith("catchall:"))
    {
    	result = parseCatchAllMessage(description)
    }
    else if (description?.startsWith('read attr -'))
    {
		results = parseReportAttributeMessage(description)
	}
    else if (description?.startsWith('on/off: '))
    {
        def value = description?.endsWith(" 1") ? "on" : "off"
        result = createEvent(name: "switch", value: value)        
        log.debug "Parse returned ${result?.descriptionText}"
    }
    else
    {    	   
        log.debug "Parse returned ${result?.descriptionText}"
    }

    return result
}

private Map parseCatchAllMessage(String description) {
 	def result = [:]
 	def cluster = zigbee.parse(description)
 
	if (cluster.clusterId == 0x0006 &&
    	cluster.data[0]==0 && cluster.data[1]==0 &&		//Attribute 0
        cluster.data[2] == 0 &&							//Success!
        cluster.data[3] == 0x10							//Boolean
        )
    {       
        def value = cluster.data[4];
        
        log.debug "Message from endpoint ${cluster.sourceEndpoint} value -> ${value}"
    
        switch(cluster.sourceEndpoint)
        {
           case 3:
            	if (value == 0)
                	result = createEvent(name: "button3", value: "pushed")                     
                else
            		 result = createEvent(name: "button3", value: "default")                
            	break;
           case 4:
            	if (value == 0)
            		 result = createEvent(name: "button4", value: "pushed")
                else
            		 result = createEvent(name: "button4", value: "default")                
            	break;
           case 5:
            	if (value == 0)
            		 result = createEvent(name: "button5", value: "pushed")
                else
            		 result = createEvent(name: "button5", value: "default")                
            	break;                
           case 6:
            	if (value == 0)
            		 result = createEvent(name: "button6", value: "pushed")
                else
            		 result = createEvent(name: "button6", value: "default")                
            	break;
           case 7:
            	if (value == 0)
            		 result = createEvent(name: "button7", value: "pushed")
                else
            		 result = createEvent(name: "button7", value: "default")                
            	break;
           case 8:
            	if (value == 0)
            		 result = createEvent(name: "button8", value: "pushed")
                else
            		 result = createEvent(name: "button8", value: "default")                
            	break;
           case 9:
            	if (value == 0)
            		 result = createEvent(name: "button9", value: "pushed")
                else
            		 result = createEvent(name: "button9", value: "default")                
            	break;
           case 10:
            	if (value == 0)
            		 result = createEvent(name: "button10", value: "pushed")
                else
            		 result = createEvent(name: "button10", value: "default")                
            	break;
        }   
        
        buttonEvent(cluster.sourceEndpoint - 2, value != 0);
        
     }
     else if (cluster.clusterId == 0x0095)
     {
     	log.debug "Xbee stuffs"
     }
     else
     {
        log.debug "Received a catchall message for ${cluster.clusterId}"      
	    log.debug "Parsed cluster is ${cluster}"
     }   

	return result
}

private buttonEvent(button, held) {
	button = button as Integer
    
    log.debug "Sending button event ${button} ${held}"
    
	if (held)
    {
		sendEvent(name: "button", value: "default", data: [buttonNumber: button], descriptionText: "$device.displayName button $button was default", isStateChange: true)
	}
    else
    {
		sendEvent(name: "button", value: "pushed", data: [buttonNumber: button], descriptionText: "$device.displayName button $button was pushed", isStateChange: true)
	}
}

private parseReportAttributeMessage(String description) {
	Map descMap = (description - "read attr - ").split(",").inject([:]) { map, param ->
		def nameAndValue = param.split(":")
		map += [(nameAndValue[0].trim()):nameAndValue[1].trim()]
	}
	log.debug "Desc Map: $descMap"

	def results = []
    
    /*
	if (descMap.cluster == "0001" && descMap.attrId == "0020") {
		log.debug "Received battery level report"
		results = createEvent(getBatteryResult(Integer.parseInt(descMap.value, 16)))
	}
	*/
    
	return results
}

def refresh() {
	log.debug "sending refresh command"
 	// Read Destination EP 0x3, Cluster 0x6 Attribute ID 0x0000 (Current Temperature)
    "st rattr 0x${device.deviceNetworkId} 0x00FF 0x0006 0x0000"   // Read Current Value 
}