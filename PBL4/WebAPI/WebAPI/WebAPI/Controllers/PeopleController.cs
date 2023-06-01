using Google.Protobuf.WellKnownTypes;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Net;
using System.Security.Principal;
using WebAPI.Data;
using WebAPI.Model;

namespace WebAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class PeopleController : ControllerBase
    {
        private readonly MyDbContext _context;
        public PeopleController(MyDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult GetPeople()
        {
            try
            {
                List<PersonModel> list = new List<PersonModel>();
                foreach (Person i in _context.People.ToList())
                {
                    PersonModel personModel = new PersonModel
                    {
                        ID_Account = i.ID_Account,
                        Name = i.Name,
                        Gender = (bool)i.Gender,
                        DateOfBirth = (DateTime)i.DateOfBirth,
                        Email = i.Email,
                        PhoneNumber = i.PhoneNumber
                    };
                    list.Add(personModel);
                }
                return Ok(list);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet]
        public IActionResult GetPerson(int? id)
        {
            try
            {
                var g = _context.People.FirstOrDefault(u => u.ID_Account == id);
                if (g != null)
                {
                    PersonModel personModel = new PersonModel
                    {
                        ID_Account = g.ID_Account,
                        Name = g.Name,
                        Gender = (bool)g.Gender,
                        DateOfBirth = (DateTime)g.DateOfBirth,
                        Email = g.Email,
                        PhoneNumber = g.PhoneNumber,
                    };
                    return Ok(personModel);
                }
                else
                {
                    return NotFound("Not Found");
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet]
        public IActionResult GetPersonNull()
        {
            try
            {
                List<int> list1 = new List<int>();
                List<int> list2 = new List<int>();
                var a = _context.Accounts.ToList();
                var b = _context.People.ToList();
                foreach (var account in a)
                {
                    list1.Add((int)account.ID_Account);

                }
                foreach (var people in b)
                {
                    list2.Add(people.ID_Account);
                }
                var list3 = list1.Except(list2);
                return Ok(list3);

            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }


        [HttpPut]
        public IActionResult PutPerson([FromBody]PersonModel personModel)
        {
            try
            {
                var g = _context.People.FirstOrDefault(u => u.ID_Account == personModel.ID_Account);
                if (g != null)
                {
                    g.Name = personModel.Name;
                    g.Gender = personModel.Gender;
                    g.DateOfBirth = personModel.DateOfBirth;
                    g.Email = personModel.Email;
                    g.PhoneNumber = personModel.PhoneNumber;
                    _context.SaveChanges();
                    return Ok("Success");
                }
                else
                {
                    return NotFound("Not Found");
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost]
        public IActionResult PostPerson(PersonModel personModel)
        {
            try
            {
                var g = _context.People.FirstOrDefault(u => u.ID_Account == personModel.ID_Account);
                if (g == null)
                {
                    Person person = new Person
                    {
                        ID_Account = personModel.ID_Account,
                        Name = personModel.Name,
                        Gender = personModel.Gender,
                        DateOfBirth = personModel.DateOfBirth,
                        Email = personModel.Email,
                        PhoneNumber = personModel.PhoneNumber,
                    };

                    _context.People.Add(person);
                    _context.SaveChanges();
                    return Ok(personModel);
                }
                else
                {
                    return BadRequest("Person already exists");
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

            [HttpDelete]
        public async Task<IActionResult> DeletePerson(int? id)
        {
            var person = await _context.People.FindAsync(id);
            if (person == null)
            {
                return NotFound();
            }

            _context.People.Remove(person);
            await _context.SaveChangesAsync();
            return Ok("Success");
        }

    }

}
