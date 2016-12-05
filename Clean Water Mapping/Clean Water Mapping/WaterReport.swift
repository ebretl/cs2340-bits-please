//
//  WaterReport.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/4/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import Foundation

class WaterReport {
    let reportNumber : Int
    let dateAndTime : Date
    let creatingUser : String
    let location : String
    let waterType : String
    let waterCondition : String
    
    init(reportNumber : Int, location : String, waterType : String, waterCondition : String) {
        self.reportNumber = reportNumber
        self.dateAndTime = Date()
        self.creatingUser = Data.sharedInstance.currentUser.getUsername()
        self.location = location
        self.waterType = waterType
        self.waterCondition = waterCondition
    }
}
