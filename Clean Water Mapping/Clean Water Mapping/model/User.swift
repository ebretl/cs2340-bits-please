//
//  User.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/3/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import Foundation

class User {
    //Properties
    let username : String
    var fullName : String
    var password : String
    var ban : Bool
    let type : UserTypeEnum
    var emailAddr : String
    var incorrectPasswordCount : Int = 0
    
    init() {
        self.username = ""
        self.fullName = ""
        self.password = ""
        self.ban = false
        self.type = UserTypeEnum.user
        self.emailAddr = ""
    }
    
    init(username : String, fullName : String, password : String, ban : Bool, type : UserTypeEnum, emailAddr : String) {
        self.username = username
        self.fullName = fullName
        self.password = password
        self.ban = ban
        self.type = type
        self.emailAddr = emailAddr
    }
    
    
    //Getters
    public func getUsername() -> String {
        return username
    }
    
    public func getFullName() -> String {
        return fullName
    }
    
    public func isBanned() -> Bool {
        return ban
    }
    
    public func getType() -> UserTypeEnum {
        return type
    }
    
    public func getEmailAddr() -> String {
        return emailAddr
    }

    
    //Check
    public func checkUserBlocked() -> Bool {
        return incorrectPasswordCount >= 3
    }
    
    public func checkPassword(password : String) -> Bool {
        return self.password == password
    }
    
    
    //Setters
    public func setFullName(fullName : String) {
        self.fullName = fullName
    }
    
    public func setBan() {
        self.ban = true
    }
    
    public func setEmailAddr(emailAddr : String) {
        self.emailAddr = emailAddr
    }
    
    public func increaseIncorrectPasswordCount() {
        self.incorrectPasswordCount += 1
    }
}
