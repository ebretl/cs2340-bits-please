//
//  UserListVC.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/5/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import UIKit

class UserListVC: UITableViewController {

    let userList = Array(Data.sharedInstance.userList.values)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.register(UINib(nibName: "UserCell", bundle: nil), forCellReuseIdentifier: "User")

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return Data.sharedInstance.userList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "User", for: indexPath) as! UserCell
        let user = userList[indexPath.row]
        cell.fullNameLabel.text = user.getFullName()
        cell.usernameLabel.text = user.getUsername()
        if (user.isBanned()) {
            cell.bannedLabel.text = "(BANNED)"
        } else {
            cell.bannedLabel.text = ""
        }
        if (user.checkUserBlocked()) {
            cell.blockedLabel.text = "(BLOCKED)"
        } else {
            cell.blockedLabel.text = ""
        }
        switch user.getType() {
        case .user:
            cell.userTypeLabel.text = "User"
        case .worker:
            cell.userTypeLabel.text = "Worker"
        case .manager:
            cell.userTypeLabel.text = "Manager"
        case .admin:
            cell.userTypeLabel.text = "Admin"
        }
        return cell
    }
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 110
    }

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    override func tableView(_ tableView: UITableView, editActionsForRowAt indexPath: IndexPath) -> [UITableViewRowAction]? {
        //delete
        
        //ban
        
        //unban
        
        //block
        
        //unblock
        return nil
    }
    
    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

    @IBAction func logout(button: UIBarButtonItem) {
        Data.sharedInstance.currentUser = User()
        
        //go back to welcome screen
        let storyboard = UIStoryboard.init(name: "Main", bundle: nil)
        let viewController = storyboard.instantiateInitialViewController()
        self.present(viewController!, animated: true, completion: nil)
    }
}
