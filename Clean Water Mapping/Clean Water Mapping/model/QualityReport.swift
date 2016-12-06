//
//  QualityReport.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/5/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import Foundation

class QualityReport {
    let reportNumber : Int
    let dateAndTime : Date
    let creatingUser : String
    let location : String
    let overallCondition : String
    let virusPPM : Float
    let contaminantPPM : Float
    
    init(reportNumber : Int, location : String, overallCondition : String, virusPPM : Float, contaminantPPM : Float) {
        self.reportNumber = reportNumber
        self.dateAndTime = Date()
        self.creatingUser = Data.sharedInstance.currentUser.getUsername()
        self.location = location
        self.overallCondition = overallCondition
        self.virusPPM = virusPPM
        self.contaminantPPM = contaminantPPM
    }
}
