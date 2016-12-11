package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.LoggedUser;

/**
 * Created by kemo on 10/12/2016.
 */
public class ClientLoggedUser extends LoggedUser {
    public ClientLoggedUser(String id) {
        super(id);
    }

    //TODO #khaled
    //>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void createGroup() {

    }

    @Override
    public void joinGroup() {

    }

    @Override
    public void exitGroup() {

    }
    //<<<<<<<<<<<<<<<<<<<<<<<<

    //TODO #kareem
    //>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void setFriends() {
        
    }

    @Override
    public void getFriends() {

    }

    @Override
    public void getNotfications() {

    }

    @Override
    public void getgroups() {
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<
    //Using inner abstract class to get results
    //as these functions run in another threads
    abstract class GetFriendReq
    {
        GetFriendReq()
        {
            Command command = new Command();
            command.setKeyWord(LoggedUser.FETCH_REQS);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                    onFinish(cmd);
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        abstract void onFinish(Command cmd);
    }
        abstract class GetStatus
        {
            GetStatus(String id)
            {
                Command command = new Command();
                command.setKeyWord(LoggedUser.GET_RELATION_STATUS);
                command.setSharableObject(id);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                    @Override
                    void analyze(Command cmd) {
                        onFinish(cmd.getObjectStr());
                    }
                };
            CommandsExecutor.getInstance().add(commandRequest);
            }
            abstract void onFinish(String s);
        }

        abstract class AcceptFriendReq
        {
            AcceptFriendReq(String id)
            {
                Command command = initialize(id);
                command.setKeyWord(LoggedUser.ACCEPT_FRIEND);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                    @Override
                    void analyze(Command commandFromServer) {
                        onFinish(commandFromServer);
                    }
                };
                CommandsExecutor.getInstance().add(commandRequest);
            }
            abstract void onFinish(Command cmd);
        }

        abstract class DeclineFriendReq
        {
            DeclineFriendReq(String id)
            {
                Command command = initialize(id);
                command.setKeyWord(LoggedUser.DECLINE_FRIEND);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                    @Override
                    void analyze(Command commandFromServer) {
                      onFinish(commandFromServer);
                    }
                };
                CommandsExecutor.getInstance().add(commandRequest);
            }
            abstract void onFinish(Command cmd);
        }
        abstract class RemoveFriend
        {
            RemoveFriend(String id)
            {
                Command command = initialize(id);
                command.setKeyWord(LoggedUser.REMOVE_FRIEND);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                    @Override
                    void analyze(Command commandFromServer) {
                        onFinish(commandFromServer);
                    }
                };
                CommandsExecutor.getInstance().add(commandRequest);
            }
            abstract void onFinish(Command cmd);
        }
        abstract class CancelFriendReq
        {
            CancelFriendReq(String id)
            {
                Command command = initialize(id);
                command.setKeyWord(LoggedUser.CANCEL_FRIEND_REQ);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                    @Override
                    void analyze(Command commandFromServer) {
                        onFinish(commandFromServer);
                    }
                };
                CommandsExecutor.getInstance().add(commandRequest);
            }
            abstract void onFinish(Command cmd);
        }
    abstract class addFriend
    {
        addFriend(String id)
        {
            Command command = initialize(id);
            command.setKeyWord(LoggedUser.ADD_FRIEND);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                @Override
                void analyze(Command commandFromServer) {
                   onFinish(commandFromServer);
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        abstract void onFinish(Command cmd);
    }
    private Command initialize(String id)
    {
        Command command = new Command();
        command.setSharableObject(id);
        return command;
    }
}