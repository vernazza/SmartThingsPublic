/**
 *  Virtual Button
 *
 *  Copyright 2015 obycode
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
	definition (name: "Virtual Button", namespace: "vernazza", author: "obycode") {
		capability "Button"
		capability "Sensor"

	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
		standardTile("button", "device.button", canChangeIcon: true, inactiveLabel: false, width: 2, height: 2) {
			state "default", label: 'Libero', icon: "st.secondary.off"
			state "pushed", label: 'Premuto', icon: "st.indicators.lit-when-on", backgroundColor: "#66ccff"
			state "held", label: 'Tenuto', icon: "st.indicators.lit-when-on", backgroundColor: "#0066ff"
		}

		main "button"
		details(["button"])
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
	if (description == "updated") {
    	sendEvent(name: "button", value: "released", data: [buttonNumber: 1])
    }
}

// handle commands
def push() {
	log.debug "Executing 'push'"
    sendEvent(name: "button", value: "pushed", data: [buttonNumber: 1], descriptionText: "$device.displayName button $button was pressed", isStateChange: true)
}

def hold() {
	log.debug "Executing 'hold'"
	sendEvent(name: "button", value: "held", data: [buttonNumber: 1], descriptionText: "$device.displayName button $button was held", isStateChange: true)
}

def release() {
	log.debug "Executing 'release'"
	sendEvent(name: "button", value: "default", data: [buttonNumber: 1], descriptionText: "$device.displayName button $button was held", isStateChange: true)
}