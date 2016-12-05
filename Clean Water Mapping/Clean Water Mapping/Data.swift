//
//  Data.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/4/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import Foundation

class Data {
    static let sharedInstance = Data()
    
    var currentUser : User
    var nextReportNumber = 0
    
    var userList : [String : User]
    var waterReportList : [WaterReport]
    
    init() {
        currentUser = User()
        userList = [:]
        waterReportList = []
    }
    
    func registerUser(user : User) -> Bool {
        if (userList[user.getUsername()] != nil) {
            return false
        } else {
            currentUser = user
            userList[user.getUsername()] = user
            return true
        }
    }
    
    func login(username : String, password : String) -> User {
        guard let user = userList[username] else {
            //user not in data
            return User()
        }
        if (user.checkUserBlocked()) {
            //incorrect password
            let returnUser = User()
            returnUser.increaseIncorrectPasswordCount()
            returnUser.increaseIncorrectPasswordCount()
            returnUser.increaseIncorrectPasswordCount()
            return returnUser
        } else if (user.checkPassword(password: password)) {
            //logged in
            currentUser = user
            return user
        } else {
            user.increaseIncorrectPasswordCount()
            return User()
        }
    }
    
    func submitWaterReport(location : String, waterType : String, waterCondition : String) {
        let waterReport = WaterReport.init(reportNumber: nextReportNumber, location: location, waterType: waterType, waterCondition: waterCondition)
        waterReportList.append(waterReport)
        nextReportNumber += 1
    }
}
