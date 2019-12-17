#! /usr/bin/bash
FILENAME=db$(date +"%m_%d_%Y-%H_%M_%S")
./lua2json.sh < 'E:\Program Files (x86)\World of Warcraft\_classic_\WTF\Account\KERIMSBOZ\SavedVariables\AuctionDB.lua' | head -n -3 > ../data/$FILENAME
echo "]" >> ../data/$FILENAME
echo "}" >> ../data/$FILENAME
echo "}" >> ../data/$FILENAME




