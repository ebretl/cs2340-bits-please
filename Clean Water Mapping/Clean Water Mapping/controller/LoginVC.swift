//
//  LoginVC.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/3/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import UIKit

class LoginVC: UIViewController {
    
    @IBOutlet weak var usernameField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    @IBOutlet weak var alertLabel : UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func login(button: UIButton) {
        let username : String = usernameField.text!
        let password : String = passwordField.text!
        if (username.isEmpty || password.isEmpty) {
            alertLabel.text = "Please enter both your username and password."
            return
        } else {
            
        }
    }
}

