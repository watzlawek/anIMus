package encryption.org.watzlawek;

import java.util.Vector;
import java.lang.String;
import android.content.*;
import org.jivesoftware.smack.packet.*;


public class Encryption {
	private Vector<String> mMemberList;
	private String context;
	 
	
	
	/**
	 * Constructor 
	 * @param context
	 */
	public Encryption (Context context){
		
	}
	
	/**
	 * On-/Offline Schluesseltausch
	 */
	private String PreKeyWisperMessage() {
		return "";
	}
	
	private String PreKey() {
		return "";
	}
	
	private String KeyExchangeMessage() {
		return "";
	}
	
	public Message decryptMessage(MessageKey MK, Message cipher) throws EncryptionFaultException {
		/**
		 * Muessen noch deklariert werden:

		message_key MK;
		Np  : Purported message number
		PNp : Purported previous message number
		CKp : Purported new chain key
		DHp : Purported new DHr
		RKp : Purported new root key
		NHKp, HKp : Purported new header keys*/
		
		/**
		 * Attempt to decrypt the message
		  with skipped-over message keys (and their associated header keys) from
		  persistent storage.
		  */
		try_skipped_header_and_message_keys();
		
		/**
		 * Given a current header key,
		  a current message number, a future message number, and a chain key,
		  calculates and stores all skipped-over message keys (if any) in a
		  staging area where they can later be committed, along with their
		  associated header key.  Returns the chain key and message key
		  corresponding to the future message number.  If passed a chain key
		  with value <none>, this function does nothing.
		*/
		stage_skipped_header_and_message_keys();
		
		/**
		 * Commits any skipped-over
		  message keys from the staging area to persistent storage (along 
		  with their associated header keys).
		*/
		commit_skipped_header_and_message_keys();
		
		if (plaintext = try_skipped_header_and_message_keys())
			return plaintext;
		
		if (HKr != 0 && Dec(HKr, header)){
			Np = read();
			MK = stage_skipped_header_and_message_keys(HKr, Nr, Np, CKr);
			CKp = MK;
			if (!Dec(MK, ciphertext))
				raise undecryptable;
		}
		else{
			if (ratchet_flag || !Dec(NHKr, header))
				raise undecryptable();
			Np = read();
			PNp = read();
			DHRp = read();
			stage_skipped_header_and_message_keys(HKr, Nr, PNp, CKr);
			HKp = NHKr;
			CKp = KDF( HMAC-HASH(RK, DH(DHRp, DHRs)) );
			RKp = CKp;
			NHKp = CKp; 
			MK = stage_skipped_header_and_message_keys(HKp, 0, Np, CKp);
			CKp = MK;
			if (!Dec(MK, ciphertext))
				raise undecryptable();
			RK = RKp;
			HKr = HKp;
			NHKr = NHKp;
			DHRr = DHRp;
			erase(DHRs);
			ratchet_flag = true;
			commit_skipped_header_and_message_keys();
			Nr = Np + 1;
			CKr = CKp;
			return read();
		}
	}
	
	public Message encryptMessage(MessageKey MK, Message text) throws EncryptionFaultException {
		message_key MK;

		if (ratchet_flag) {
			DHRs = generateECDH();
			HKs = NHKs;
			CKs = KDF( HMAC-HASH(RK, DH(DHRs, DHRr)) );
			RK = CKs;
			NHKs = CKs;
			PNs = Ns;
			Ns = 0;
			ratchet_flag = false;
		}

		MK = HMAC-HASH(CKs, "0");
		text = Enc(HKs, Ns || PNs || DHRs) || Enc(MK, plaintext);
		Ns = Ns + 1;
		CKs = HMAC-HASH(CKs, "1");

		return text;
	}
	
	public void setMemberList(Vector<String> mMemberList){
		this.mMemberList = mMemberList; 
	}
	
	
	
}