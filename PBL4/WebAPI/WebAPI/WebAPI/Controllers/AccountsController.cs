using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebAPI.Data;
using WebAPI.Model;

namespace WebAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class AccountsController : ControllerBase
    {
        private readonly MyDbContext _context;

        public AccountsController(MyDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult GetAccounts()
        {
            List<AccountModel> listModel = new List<AccountModel>();
            foreach (Account account in _context.Accounts.ToList())
            {
                AccountModel accountModel = new AccountModel
                {
                    ID_Account = (int)account.ID_Account,
                    UserName = account.UserName,
                    Password = account.Password,
                    Position = (int)account.Position
                };
                listModel.Add(accountModel);
            }
            return Ok(listModel);
        }

        [HttpGet]
        public IActionResult GetAccountByID(int? id)
        {
            Account account = _context.Accounts.FirstOrDefault( u=> u.ID_Account == id);

            if (account == null)
            {
                return NotFound();
            }
            else
            {
                AccountModel accountModel = new AccountModel
                {
                    ID_Account = (int)account.ID_Account,
                    UserName = account.UserName,
                    Password = account.Password,
                    Position = (int)account.Position
                };
                return Ok(accountModel);
            }
        }

        [HttpGet]
        public IActionResult GetAccount(string UserName, string Password)
        {
            Account account = _context.Accounts.FirstOrDefault(u => u.UserName == UserName &&
            u.Password == Password);

            if (account == null)
            {
                return BadRequest("Account does not exist");
            }
            else
            {
                AccountModel accountModel = new AccountModel
                {
                    ID_Account = (int)account.ID_Account,
                    UserName = account.UserName,
                    Password = account.Password,
                    Position = (int)account.Position,
                };
                return Ok(accountModel);
            }
        }
       


        [HttpGet]
        public IActionResult CheckUserName(string UserName)
        {
            Account account = _context.Accounts.FirstOrDefault(u => u.UserName == UserName);

            if (account == null)
            {
                return Ok(0);
            }
            else
            {
                return Ok(account.ID_Account);
            }
        }
        [HttpGet]
        public IActionResult CheckPass(int ID_Account, string Password)
        {
            Account account = _context.Accounts.FirstOrDefault(u => u.ID_Account == ID_Account && u.Password == Password);

            if (account == null)
            {
                return Ok(0);
            }
            else
            {
                return Ok(account.ID_Account);
            }
        }

        [HttpPut]
        public IActionResult PutAccount(AccountModel accountModel)
        {
            try
            {
                var g = _context.Accounts.FirstOrDefault(u => u.ID_Account == accountModel.ID_Account);

                if (g != null)
                {
                    g.UserName = accountModel.UserName;
                    g.Password = accountModel.Password;
                    g.Position = accountModel.Position;
                    _context.SaveChanges();
                    return Ok(accountModel);
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
        [HttpPut]
        public IActionResult PutAccountPass(int ID_Account, string newPass)
        {
            try
            {
                var g = _context.Accounts.FirstOrDefault(u => u.ID_Account == ID_Account);

                if (g != null)
                {
                    g.Password = newPass;
                    _context.SaveChanges();
                    return Ok(ID_Account);
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost]
        public IActionResult PostAccount(AccountModelPost accountModelPost)
        {
            try
            {
                var g = _context.Accounts.FirstOrDefault(u => u.UserName == accountModelPost.UserName);
                if (g != null)
                {
                    return NotFound("This account already exists");
                }
                else
                {
                    Account account = new Account
                    {
                        UserName = accountModelPost.UserName,
                        Password = accountModelPost.Password,
                        Position = accountModelPost.Position,
                    };
                    _context.Accounts.Add(account);
                    _context.SaveChanges();
                    return Ok(account.ID_Account);
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
        [HttpPost]
        public IActionResult PostAccountText(string UserName, string Password)
        {
            try
            {
                var g = _context.Accounts.FirstOrDefault(u => u.UserName == UserName);
                if (g != null)
                {
                    return NotFound("This account already exists");
                }
                else
                {
                    Account account = new Account
                    {
                        UserName = UserName,
                        Password = Password,
                        Position = 1,
                    };
                    _context.Accounts.Add(account);
                    _context.SaveChanges();
                    return Ok(account.ID_Account);
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }


        [HttpDelete]
        public async Task<IActionResult> DeleteAccount(int? id)
        {
            var account = await _context.Accounts.FindAsync(id);
            if (account == null)
            {
                return NotFound();
            }
            _context.Accounts.Remove(account);
            await _context.SaveChangesAsync();
            return Ok("Success ID : " + account.ID_Account);
        }

    }
}
