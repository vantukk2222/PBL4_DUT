using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebAPI.Data;
using WebAPI.Model;

namespace WebAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class CartsController : ControllerBase
    {
        private readonly MyDbContext _context;
        public CartsController(MyDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult GetCartByIdAccount(int id_Account)
        {
            try
            {
                List<CartModel> list = new List<CartModel>();
                var g = from Cart in _context.Carts
                        where Cart.ID_Account == id_Account
                        select Cart;
                if (g.Count() > 0)
                {
                    foreach (Cart i in g)
                    {
                        CartModel model = new CartModel
                        {
                            ID_Cart = i.ID_Cart,
                            ID_Account = i.ID_Account,
                            ID_Sach = i.ID_Sach,
                            SoLuong = i.SoLuong,
                            TongTien = i.TongTien
                        };
                        list.Add(model);
                    }
                    return Ok(list);
                }
                else
                {
                    return Ok(null);
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
        [HttpGet]
        public IActionResult GetCartByID(int id_Account, int id_Sach)
        {
            try
            {
                var g = from Cart in _context.Carts
                        where Cart.ID_Account == id_Account && Cart.ID_Sach == id_Sach
                        select Cart;
                if (g.Count() > 0)
                {
                    foreach (Cart i in g)
                    {
                        CartModel model = new CartModel
                        {
                            ID_Cart = i.ID_Cart,
                            ID_Account = i.ID_Account,
                            ID_Sach = i.ID_Sach,
                            SoLuong = i.SoLuong,
                            TongTien = i.TongTien
                        };
                    }
                    return Ok(id_Account);
                }
                else
                {
                    return Ok(null);
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPut]
        public IActionResult PutCart(CartModel Model)
        {
            try
            {
                var g = _context.Carts.FirstOrDefault(u => u.ID_Cart == Model.ID_Cart);
                if (g != null)
                {
                    g.ID_Account = Model.ID_Account;
                    g.ID_Sach = Model.ID_Sach;
                    g.SoLuong = Model.SoLuong;
                    g.TongTien = Model.TongTien;
                    _context.SaveChanges();
                    return Ok(Model.ID_Cart);
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
        [HttpPut]
        public IActionResult PutCartText(int ID_Account, int ID_Sach, int SoLuong, int TongTien)
        {
            try
            {
                var g = _context.Carts.FirstOrDefault(u => u.ID_Account == ID_Account && u.ID_Sach == ID_Sach);
                if (g != null)
                {
                    g.SoLuong += SoLuong;
                    g.TongTien += TongTien;
                    _context.SaveChanges();
                    return Ok(ID_Sach);
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
        public IActionResult PostCart(CartModelPost Model)
        {
            try
            {
                Cart cart = new Cart
                {
                    ID_Account = Model.ID_Account,
                    ID_Sach = Model.ID_Sach,
                    SoLuong = Model.SoLuong,
                    TongTien = Model.TongTien
                };
                _context.Carts.Add(cart);
                _context.SaveChanges();
                return Ok(Model);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpDelete]
        public async Task<IActionResult> DeleteCart(int? id)
        {
            var cart = await _context.Carts.FindAsync(id);
            if (cart == null)
            {
                return NotFound();
            }
            _context.Carts.Remove(cart);
            await _context.SaveChangesAsync();
            return Ok(id);
        }

    }

}

